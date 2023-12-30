package com.alonso.xmlconverter.aws;

import com.alonso.xmlconverter.ConverterProcessor;
import com.alonso.xmlconverter.XmlconverterApplication;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.lambda.powertools.logging.Logging;
import software.amazon.lambda.powertools.metrics.Metrics;
import software.amazon.lambda.powertools.tracing.Tracing;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@NoArgsConstructor
public class LambdaHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

	private static final Logger log = LogManager.getLogger(LambdaHandler.class);

	// Static instead of constructor for more performance on cold startup times.
	static {
		try {
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(XmlconverterApplication.class);
		} catch (ContainerInitializationException e) {
			e.printStackTrace();
			throw AwsServiceException.builder().message("Could not initialize Spring Boot application").build();
		}
	}

	// Spring Cloud Function was an option to abstract this logic and make it agnostic
	// But I chose to implement AWS and AZURE independently

	@Logging
	@Tracing
	@Metrics
	@Override
	public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
		ConverterProcessor processor = new ConverterProcessor();
		AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
		log.debug("AWS Proxy Request: %s", awsProxyRequest);
		String input = awsProxyRequest.getBody();
		InputStream targetStream = new ByteArrayInputStream(input.getBytes());

		String output = processor.convert(targetStream);
		log.debug("Response Output: %s", output);
		awsProxyResponse.setStatusCode(200);
		awsProxyResponse.setBody(output);
		return awsProxyResponse;
	}
}
