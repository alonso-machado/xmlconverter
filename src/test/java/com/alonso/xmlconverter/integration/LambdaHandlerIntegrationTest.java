package com.alonso.xmlconverter.integration;

import com.alonso.xmlconverter.aws.LambdaHandler;
import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class LambdaHandlerIntegrationTest {
    /*

    @Test
    public void testHandleRequest() throws Exception {
        // Arrange
        LambdaHandler lambdaHandler = new LambdaHandler();
        File xmlFile = new File("src/test/resources/inputExample.xml");
        InputStream inputStream = new FileInputStream(xmlFile);
        OutputStream outputStream = new ByteArrayOutputStream();
        Context context = null;

        // Act
        lambdaHandler.handleRequest(inputStream, outputStream, context);

        // Assert
        String response = outputStream.toString();
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.contains("\"id\": 1254"));
        Assertions.assertTrue(response.contains("\"originalAmount\": \"1520.00\""));
        Assertions.assertTrue(response.contains("\"paidScheduleNumber\": 5"));
    }
    */
}