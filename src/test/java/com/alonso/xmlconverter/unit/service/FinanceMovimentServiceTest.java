package com.alonso.xmlconverter.unit.service;

import com.alonso.xmlconverter.mapper.FinanceMovimentMapper;
import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import com.alonso.xmlconverter.model.output.FinanceMoviment;
import com.alonso.xmlconverter.model.output.OperationTypeEnum;
import com.alonso.xmlconverter.model.output.PaymentTypeEnum;
import com.alonso.xmlconverter.service.FinanceMovimentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FinanceMovimentServiceTest {

	@Mock
	private FinanceMovimentMapper financeMovimentMapper;

	private FinanceMovimentService financeMovimentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		financeMovimentService = new FinanceMovimentService(financeMovimentMapper);
	}

	@Test
	void whenConvertMocked_thenReturnExpected() throws JAXBException, FileNotFoundException {
		//Arrange
		JAXBContext context = JAXBContext.newInstance(MovimentacaoFinanceira.class);
		MovimentacaoFinanceira xmlEntity = (MovimentacaoFinanceira) context.createUnmarshaller().unmarshal(new FileReader("src/test/resources/inputExample.xml"));

		FinanceMoviment expectedOutput = new FinanceMoviment(); // Empty Finance for return in Mock

		//Mockito for the mapper
		when(financeMovimentMapper.toFinanceMoviment(xmlEntity)).thenReturn(expectedOutput);

		// Act
		FinanceMoviment actualOutput = financeMovimentService.convert(xmlEntity);

		// Assert
		Assertions.assertEquals(expectedOutput, actualOutput);
	}
}