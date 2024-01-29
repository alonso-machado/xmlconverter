package com.alonso.xmlconverter;

import com.alonso.xmlconverter.adapters.LocalDateGsonAdapter;
import com.alonso.xmlconverter.exceptions.XMLFaultyDataException;
import com.alonso.xmlconverter.mapper.FinanceMovimentMapper;
import com.alonso.xmlconverter.mapper.FinanceMovimentMapperImpl;
import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import com.alonso.xmlconverter.model.output.FinanceMoviment;
import com.alonso.xmlconverter.service.FinanceMovimentService;
import com.alonso.xmlconverter.service.MovimentacaoFinanceiraService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.time.LocalDate;

// Class that convert the XML InputStream to JSON to maintain the logic here and Platform Agnostic
// This can be consumed by the AWS Lambda Function or Azure Serverless Function or GCP Cloud Run
@NoArgsConstructor
public class ConverterProcessor {

	public String convert(InputStream inputStream){
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter())
				.create();
		MovimentacaoFinanceiraService movimentacaoFinanceiraService = new MovimentacaoFinanceiraService();
		FinanceMovimentMapper financeMovimentMapper = new FinanceMovimentMapperImpl();
		FinanceMovimentService financeMovimentService = new FinanceMovimentService(financeMovimentMapper);
		MovimentacaoFinanceira movimentacaoFinanceira;
		FinanceMoviment financeMoviment;
		try {
			movimentacaoFinanceira = movimentacaoFinanceiraService.marshalInput(inputStream);
		} catch (JAXBException | NullPointerException e) {
			throw new XMLFaultyDataException(e.getMessage());
		}
		try {
			financeMoviment = financeMovimentService.convert(movimentacaoFinanceira);
		} catch (NullPointerException e) {
			throw new XMLFaultyDataException(e.getMessage());
		}
		return gson.toJson(financeMoviment);
	}
}
