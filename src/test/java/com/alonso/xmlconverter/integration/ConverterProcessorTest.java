package com.alonso.xmlconverter.integration;

import com.alonso.xmlconverter.ConverterProcessor;
import com.alonso.xmlconverter.exceptions.XMLFaultyDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

class ConverterProcessorTest {

	@Test
	void whenConvertwithFullData_thenReturnFullJson() throws FileNotFoundException {
		// Arrange
		File xmlFile = new File("src/test/resources/inputExample.xml");
		InputStream inputStream = new FileInputStream(xmlFile);
		ConverterProcessor converterProcessor = new ConverterProcessor();

		// Act
		String result = converterProcessor.convert(inputStream);

		// Assert
		Assertions.assertNotNull(result);
	}

	@Test
	void whenConvertWithRandomData_thenShouldThrow() {
		// Arrange
		InputStream inputStream = new ByteArrayInputStream("random data".getBytes());
		ConverterProcessor converterProcessor = new ConverterProcessor();

		// Act
		Assertions.assertThrows(XMLFaultyDataException.class, () -> converterProcessor.convert(inputStream));
	}

	@Test
	void whenConvertwithMissingData_thenReturnFullJson() throws FileNotFoundException {
		// Arrange
		File xmlFile = new File("src/test/resources/inputExampleMissingData.xml");
		InputStream inputStream = new FileInputStream(xmlFile);

		ConverterProcessor converterProcessor = new ConverterProcessor();

		// Act & Assert
		Assertions.assertThrows(XMLFaultyDataException.class, () -> converterProcessor.convert(inputStream));
	}
}