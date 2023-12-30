// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

//https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/eventhubs/azure-messaging-eventhubs-checkpointstore-blob/src/samples/java/com/azure/messaging/eventhubs/checkpointstore/blob/EventProcessorBlobCheckpointStoreSample.java
package com.alonso.xmlconverter.azure;

import com.alonso.xmlconverter.ConverterProcessor;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class EventProcessorBlobCheckpointStore {

    private static final String EH_CONNECTION_STRING = "Endpoint=sb://eh-dev-poc67.servicebus.windows.net/;";
    private static final String EH_NAME = "eh-dev-poc67";
    private static final String SAS_TOKEN_STRING = "SAS_TOKEN";
    private static final String STORAGE_CONNECTION_STRING = "DefaultEndpointsProtocol=https;AccountName=sadevpoc67;";

    private static final Logger log = LogManager.getLogger(EventProcessorBlobCheckpointStore.class);



    public static final Consumer<EventContext> PARTITION_PROCESSOR = eventContext -> {
        log.info("Processing event %s", eventContext.getEventData());
        PartitionContext partitionContext = eventContext.getPartitionContext();
        EventData eventData = eventContext.getEventData();
        byte[] data = eventData.getBody();
        InputStream targetStream = new ByteArrayInputStream(data);
        ConverterProcessor converterProcessor = new ConverterProcessor();
        String response = converterProcessor.convert(targetStream);

        EventData eventDataConverted = new EventData(response);
        Sender.publishEvents(eventDataConverted);
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