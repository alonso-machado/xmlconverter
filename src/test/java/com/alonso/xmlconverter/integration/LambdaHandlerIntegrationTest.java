package com.alonso.xmlconverter.integration;

import com.alonso.xmlconverter.aws.LambdaHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;

@SpringBootTest
class LambdaHandlerIntegrationTest {

	/*
	@Test
	void testHandleRequest() throws Exception {
		// Arrange
		LambdaHandler lambdaHandler = new LambdaHandler();
		File xmlFile = new File("src/test/resources/inputExample.xml");
		AwsProxyRequest awsProxyRequest = new AwsProxyRequest();
		awsProxyRequest.setBody(new String(Files.readAllBytes(xmlFile.toPath())));
		File jsonFile = new File("src/test/resources/outputExample.xml");
		String expectedResponse = new String(Files.readAllBytes(jsonFile.toPath()));

		// Act
		AwsProxyResponse awsProxyResponse = lambdaHandler.handleRequest(awsProxyRequest, null);
		String response = awsProxyResponse.getBody();

		// Assert
		Assertions.assertNotNull(awsProxyResponse);
		Assertions.assertEquals(200, awsProxyResponse.getStatusCode());
		Assertions.assertNotNull(response);
		Assertions.assertTrue(response.contains("\"id\": 1254"));
		Assertions.assertTrue(response.contains("\"originalAmount\": \"1520.00\""));
		Assertions.assertTrue(response.contains("\"paidScheduleNumber\": 5"));
	}
	*/
}