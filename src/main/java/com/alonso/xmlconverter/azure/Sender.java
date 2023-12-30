package com.alonso.xmlconverter.azure;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;

import java.util.Collections;

public class Sender {
    private static final String EH_CONNECTION_STRING = "Endpoint=sb://eh-dev-poc67.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=uZIMz5Dl9gqR0w4jHFUOiUrkUNGCoDien+AEhNU1CSM=";
    private static final String EH_NAME = "eh-dev-poc67";

    public static void publishEvents(EventData eventData) {
        // create a producer client
        EventHubProducerClient producer = new EventHubClientBuilder()
                .connectionString(EH_CONNECTION_STRING, EH_NAME)
                .buildProducerClient();

        producer.send(Collections.singleton(eventData));

        producer.close();
    }
}