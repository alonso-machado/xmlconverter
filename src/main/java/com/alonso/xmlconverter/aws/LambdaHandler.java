package com.alonso.xmlconverter.aws;

import com.alonso.xmlconverter.ConverterProcessor;
import com.alonso.xmlconverter.XmlconverterApplication;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.lambda.powertools.logging.Logging;
import software.amazon.lambda.powertools.metrics.Metrics;
import software.amazon.lambda.powertools.tracing.Tracing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LambdaHandler implements RequestStreamHandler {
	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

	private static final Logger log = LogManager.getLogger(LambdaHandler.class);

	// Static instead of constructor for more performance on cold startup times.
	static {
		try {
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(XmlconverterApplication.class);
		} catch (ContainerInitializationException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not initialize Spring Boot application", e);
		}
	}

	@Logging
	@Tracing
	@Metrics
	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		ConverterProcessor converterProcessor = new ConverterProcessor();
		log.info("RequestStream: " + inputStream);
		String response = converterProcessor.Convert(inputStream);
		log.info("Response: " + response);
		outputStream.write(response.getBytes());
		handler.proxyStream(inputStream, outputStream, context);
	}
}
