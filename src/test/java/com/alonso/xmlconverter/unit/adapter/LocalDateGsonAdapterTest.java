package com.alonso.xmlconverter.unit.adapter;

import com.alonso.xmlconverter.adapters.LocalDateGsonAdapter;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class LocalDateGsonAdapterTest {

    @Test
    void testSerialize() {
        // Arrange
        LocalDateGsonAdapter adapter = new LocalDateGsonAdapter();
        LocalDate date = LocalDate.parse("2022-01-01");
        JsonSerializationContext context = null;
        String expectedString = "2022-01-01";

        // Act
        JsonElement jsonElement = adapter.serialize(date, null, context);

        // Get the output as a string
        String serializedString = jsonElement.getAsString();

        // Assert
        Assertions.assertEquals(expectedString, serializedString);
    }

    @Test
    void testDeserialize() {
        // Arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expectedDate = LocalDate.of(2022, 1, 1);
        LocalDateGsonAdapter adapter = new LocalDateGsonAdapter();
        JsonElement jsonElement = new JsonPrimitive(expectedDate.format(formatter));

        // Act
        LocalDate responseDate = adapter.deserialize(jsonElement, null, null);

        // Assert
        Assertions.assertEquals(expectedDate, responseDate);
    }
}