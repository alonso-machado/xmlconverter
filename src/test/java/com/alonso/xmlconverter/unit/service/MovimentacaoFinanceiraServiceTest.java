package com.alonso.xmlconverter.unit.service;

import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import com.alonso.xmlconverter.service.MovimentacaoFinanceiraService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;

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
		File xmlFile = new File("src/test/resources/inputExample.xml");
		InputStream inputStream = new FileInputStream(xmlFile);
		movimentacaoFinanceira = movimentacaoFinanceiraService.marshalInput(inputStream);

		// Assert
		Assertions.assertEquals(1234, movimentacaoFinanceira.getMovimento().getOperacao().getId());
		Assertions.assertEquals(testDate, movimentacaoFinanceira.getMovimento().getOperacao().getDtVencimento());
		Assertions.assertEquals(10, movimentacaoFinanceira.getMovimento().getOperacao().getTotalParcelas());
		Assertions.assertEquals(BigDecimal.valueOf(1520.00), movimentacaoFinanceira.getMovimento().getOperacao().getValorOriginal());
	}
}