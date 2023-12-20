// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

//https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/eventhubs/azure-messaging-eventhubs-checkpointstore-blob/src/samples/java/com/azure/messaging/eventhubs/checkpointstore/blob/EventProcessorBlobCheckpointStoreSample.java
package com.alonso.xmlconverter.azure;

import com.alonso.xmlconverter.mapper.FinanceMovimentMapper;
import com.alonso.xmlconverter.mapper.FinanceMovimentMapperImpl;
import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import com.alonso.xmlconverter.model.output.FinanceMoviment;
import com.alonso.xmlconverter.service.FinanceMovimentService;
import com.alonso.xmlconverter.service.MovimentacaoFinanceiraService;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventProcessorClient;
import com.azure.messaging.eventhubs.EventProcessorClientBuilder;
import com.azure.messaging.eventhubs.checkpointstore.blob.BlobCheckpointStore;
import com.azure.messaging.eventhubs.models.ErrorContext;
import com.azure.messaging.eventhubs.models.EventContext;
import com.azure.messaging.eventhubs.models.PartitionContext;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.google.gson.Gson;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


public class EventProcessorBlobCheckpointStore {

    private static final String EH_CONNECTION_STRING = "Endpoint=sb://eh-dev-gft-il-poc67.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=uZIMz5Dl9gqR0w4jHFUOiUrkUNGCoDien+AEhNU1CSM=";
    private static final String EH_NAME = "eh-dev-gft-il-poc67";
    private static final String SAS_TOKEN_STRING = "?sv=2022-11-02&ss=bfqt&srt=sc&sp=rwdlacupiytfx&se=2023-12-31T00:57:14Z&st=2023-12-20T16:57:14Z&spr=https,http&sig=eWM9pwU5KbP0p1g4yQ7i%2B1cB%2FKLbdDhDCZWqTw5TGyY%3D";
    private static final String STORAGE_CONNECTION_STRING = "DefaultEndpointsProtocol=https;AccountName=sadevgftilpoc67;AccountKey=uBGnDAl0zQD+tLtwEjuTxV174RAgeDawUhnsx9lQ2858Xx6hWOslIOyLanKmzwYJwsXJHclj3iFN+AStt59Dvg==;EndpointSuffix=core.windows.net";

    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(EventProcessorBlobCheckpointStore.class);

    public static final Consumer<EventContext> PARTITION_PROCESSOR = eventContext -> {
        log.info("Processing event %s", eventContext.getEventData());
        PartitionContext partitionContext = eventContext.getPartitionContext();
        EventData eventData = eventContext.getEventData();
        byte[] data = eventData.getBody();
        Gson gson = new Gson();
        MovimentacaoFinanceiraService service = new MovimentacaoFinanceiraService();
        FinanceMovimentMapper financeMovimentMapper = new FinanceMovimentMapperImpl();
        FinanceMovimentService financeMovimentService = new FinanceMovimentService(financeMovimentMapper);
        MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();
        try {
            movimentacaoFinanceira = service.marshalInput(data);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FinanceMoviment financeMoviment = financeMovimentService.convert(movimentacaoFinanceira);
        EventData eventData2 = new EventData(gson.toJson(financeMoviment));
        Sender.publishEvents(eventData2);
    };

    public static final Consumer<ErrorContext> ERROR_HANDLER = errorContext ->
        log.info("Error occurred in partition processor for partition %s, %s.%n",
            errorContext.getPartitionContext().getPartitionId(),
            errorContext.getThrowable());

    public static void main(String[] args) throws Exception {
        BlobContainerAsyncClient blobContainerAsyncClient = new BlobContainerClientBuilder()
            .connectionString(STORAGE_CONNECTION_STRING)
            .containerName("<< CONTAINER NAME >>")
            .sasToken(SAS_TOKEN_STRING)
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
            .buildAsyncClient();

        EventProcessorClientBuilder eventProcessorClientBuilder = new EventProcessorClientBuilder()
            .connectionString(EH_CONNECTION_STRING)
            .consumerGroup("<< CONSUMER GROUP NAME >>")
            .processEvent(PARTITION_PROCESSOR)
            .processError(ERROR_HANDLER)
            .checkpointStore(new BlobCheckpointStore(blobContainerAsyncClient));

        EventProcessorClient eventProcessorClient = eventProcessorClientBuilder.buildEventProcessorClient();

        eventProcessorClient.start();

        TimeUnit.MINUTES.sleep(1); // Wait for X minutes for events to be processed.

        eventProcessorClient.stop();
    }

}