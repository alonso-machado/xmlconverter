package com.alonso.xmlconverter.integration;

import com.alonso.xmlconverter.aws.LambdaHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;

@SpringBootTest
class LambdaHandlerIntegrationTest {

	@Autowired
	LambdaHandler lambdaHandler;
	@Test
	void testHandleRequest() throws Exception {
		// Arrange
		File xmlFile = new File("src/test/resources/inputExample.xml");
		AwsProxyRequest awsProxyRequest = new AwsProxyRequest();
		awsProxyRequest.setBody(new String(Files.readAllBytes(xmlFile.toPath())));

		// Act
		AwsProxyResponse awsProxyResponse = lambdaHandler.handleRequest(awsProxyRequest, null);
		String response = awsProxyResponse.getBody();

		// Assert
		Assertions.assertNotNull(awsProxyResponse);
		Assertions.assertEquals(200, awsProxyResponse.getStatusCode());
		Assertions.assertNotNull(response);
		// These partial tests would be more meaningful but contains is not working as expected
		//Assertions.assertTrue(response.contains("\"id\": 1234"));
		//Assertions.assertTrue(response.contains("\"originalAmount\": \"1520.00\""));
		//Assertions.assertTrue(response.contains("\"paidScheduleNumber\": 5"));
		Assertions.assertEquals("{\"moviment\":{\"operation\":{\"id\":1234,\"operationType\":\"P\",\"paymentType\":\"T\",\"amountPaid\":154.5,\"originalAmount\":1520.0," +
				"\"dueDate\":\"2023-05-01\",\"amountFee\":0.25,\"hasSchedules\":\"Y\",\"totalSchedules\":10,\"paidScheduleNumber\":5,\"customer\":{\"id\":123456,\"name\":\"Maria Silva\"," +
				"\"creationDate\":\"2021-02-10\",\"birthdayDate\":\"1991-05-05\",\"gender\":\"M\",\"preferentialPayment\":\"D\"}}}}", response);

	}

}