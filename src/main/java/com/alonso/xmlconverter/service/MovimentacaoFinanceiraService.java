package com.alonso.xmlconverter.service;

import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

@Service
public class MovimentacaoFinanceiraService {

	//This mashalFile is needed for Testing from Example File XML
	public MovimentacaoFinanceira marshalFile(String xmlFile) throws JAXBException, IOException {

		JAXBContext context = JAXBContext.newInstance(MovimentacaoFinanceira.class);
		return (MovimentacaoFinanceira) context.createUnmarshaller()
				.unmarshal(new FileReader(xmlFile));
	}

	public MovimentacaoFinanceira marshalInput(InputStream inputStream) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(MovimentacaoFinanceira.class);
		return (MovimentacaoFinanceira) context.createUnmarshaller()
				.unmarshal(inputStream);
	}

}
