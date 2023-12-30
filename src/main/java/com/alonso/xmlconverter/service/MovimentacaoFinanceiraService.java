package com.alonso.xmlconverter.service;

import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.InputStream;

@Service
public class MovimentacaoFinanceiraService {

	public MovimentacaoFinanceira marshalInput(InputStream inputStream) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(MovimentacaoFinanceira.class);
		return (MovimentacaoFinanceira) context.createUnmarshaller().unmarshal(inputStream);
	}
}
