package com.alonso.xmlconverter.exceptions;

public class XMLFaultyDataException extends RuntimeException {
    public XMLFaultyDataException(String message) {
        super("Invalid XML data: " + message);
    }
}