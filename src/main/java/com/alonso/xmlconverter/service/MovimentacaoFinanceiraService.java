package com.alonso.xmlconverter.service;

import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;

@Service
public class MovimentacaoFinanceiraService {

	public MovimentacaoFinanceira marshalFile(String xmlFile) throws JAXBException, IOException {

		JAXBContext context = JAXBContext.newInstance(MovimentacaoFinanceira.class);
		return (MovimentacaoFinanceira) context.createUnmarshaller()
				.unmarshal(new FileReader(xmlFile));
	}

	public MovimentacaoFinanceira marshalInput(byte[] bytes) throws JAXBException, IOException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		JAXBContext context = JAXBContext.newInstance(MovimentacaoFinanceira.class);
		return (MovimentacaoFinanceira) context.createUnmarshaller()
				.unmarshal(inputStream);
	}

}
