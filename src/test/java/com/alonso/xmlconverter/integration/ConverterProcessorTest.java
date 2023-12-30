package com.alonso.xmlconverter.integration.service;

import com.alonso.xmlconverter.ConverterProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ConverterProcessorTest {

    @Test
    public void testConvert() throws FileNotFoundException {
        // Arrange
        File xmlFile = new File("src/test/resources/inputExample.xml");
        InputStream inputStream = new FileInputStream(xmlFile);
        ConverterProcessor converterProcessor = new ConverterProcessor();

        // Act
        String result = converterProcessor.Convert(inputStream);

        // Assert
        Assertions.assertNotNull(result);
    }
}