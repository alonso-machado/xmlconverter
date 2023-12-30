package com.alonso.xmlconverter.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

	@Override
	public BigDecimal unmarshal(String input) throws Exception {
		try {
			String withoutCommas = input.replace(',', '.');
			Double value = Double.parseDouble(withoutCommas);
			return BigDecimal.valueOf(value);
		} catch (NumberFormatException | NullPointerException e) {
			return null;
		}
	}

	@Override
	public String marshal(BigDecimal v) throws Exception {
		return v.toString();
	}
}
