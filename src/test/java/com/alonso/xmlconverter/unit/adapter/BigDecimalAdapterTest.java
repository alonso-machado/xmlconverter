package com.alonso.xmlconverter.unit.adapter;

import com.alonso.xmlconverter.adapters.BigDecimalAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class BigDecimalAdapterTest {

    @Test
    void testBigDecimalAdapterUnmarshall() throws Exception {
        BigDecimalAdapter adapter = new BigDecimalAdapter();

        // Test conversion from XML string to BigDecimal
        String xml = "56,78";
        BigDecimal expectedValue = new BigDecimal("56.78");
        BigDecimal actualValue = adapter.unmarshal(xml);
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void testBigDecimalAdapterMarshall() throws Exception {

        BigDecimalAdapter adapter = new BigDecimalAdapter();

        // Test conversion from BigDecimal to XML string
        BigDecimal input = new BigDecimal("12.34");
        String expectedString = "12.34";
        String actualXml = adapter.marshal(input);
        Assertions.assertEquals(expectedString, actualXml);
    }

    @Test
    void whenUnmarshallString_Null() throws Exception {
        BigDecimalAdapter adapter = new BigDecimalAdapter();

        // Test conversion from XML string to BigDecimal
        String xml = "aaaaaa";
        BigDecimal actualValue = adapter.unmarshal(xml);
        Assertions.assertEquals(null, actualValue);
    }
}