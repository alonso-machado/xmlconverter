package com.alonso.xmlconverter.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	DateTimeFormatter latinAmericaPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public LocalDate unmarshal(String day) throws Exception {
		return LocalDate.parse(day, latinAmericaPattern);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return latinAmericaPattern.format(v);
	}
}
