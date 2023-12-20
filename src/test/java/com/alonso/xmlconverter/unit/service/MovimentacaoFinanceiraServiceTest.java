package com.alonso.xmlconverter.unit.service;

import com.alonso.xmlconverter.mapper.FinanceMovimentMapper;
import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import com.alonso.xmlconverter.model.output.FinanceMoviment;
import com.alonso.xmlconverter.service.FinanceMovimentService;
import com.alonso.xmlconverter.service.MovimentacaoFinanceiraService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovimentacaoFinanceiraServiceTest {

	@Autowired
	private MovimentacaoFinanceiraService movimentacaoFinanceiraService;


	@Test
	void whenMarshalFile_thenReturnExpected() throws JAXBException, IOException {
		//Arrange
		MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();
		LocalDate testDate = LocalDate.parse("2023-05-01");

		// Act
		movimentacaoFinanceira = movimentacaoFinanceiraService.marshalFile("src/test/resources/inputExample.xml");

		// Assert
		Assertions.assertEquals(1234, movimentacaoFinanceira.getMovimento().getOperacao().getId());
		Assertions.assertEquals(testDate, movimentacaoFinanceira.getMovimento().getOperacao().getDtVencimento());
		Assertions.assertEquals(10, movimentacaoFinanceira.getMovimento().getOperacao().getTotalParcelas());
	}
}