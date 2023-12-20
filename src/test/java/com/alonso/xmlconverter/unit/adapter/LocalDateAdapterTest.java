package com.alonso.xmlconverter.unit.adapter;

import com.alonso.xmlconverter.adapters.LocalDateAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocalDateAdapterTest {

    @Test
    void testLocalDateAdapterMarshall() throws Exception {
        LocalDateAdapter adapter = new LocalDateAdapter();

        // Test conversion from LocalDate to XML string
        LocalDate input = LocalDate.parse("2023-11-12");
        String expectedString = "12/11/2023";
        String actual = adapter.marshal(input);
        Assertions.assertEquals(expectedString, actual);

    }

    @Test
    void testLocalDateAdapterUnmarshal() throws Exception {

        LocalDateAdapter adapter = new LocalDateAdapter();
        // Test conversion from XML string to LocalDate
        String xml = "01/01/2020";
        LocalDate expectedValue = LocalDate.parse("2020-01-01");
        LocalDate actualValue = adapter.unmarshal(xml);
        Assertions.assertEquals(expectedValue, actualValue);
    }
}