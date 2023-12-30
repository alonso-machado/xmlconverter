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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FinanceMovimentServiceTest {

	@Autowired
	private FinanceMovimentService financeMovimentService;

	@Test
	void whenConvertMocked_thenReturnExpected() throws JAXBException, FileNotFoundException {
		//Arrange
		JAXBContext context = JAXBContext.newInstance(MovimentacaoFinanceira.class);
		MovimentacaoFinanceira xmlEntity = (MovimentacaoFinanceira) context.createUnmarshaller().unmarshal(new FileReader("src/test/resources/inputExample.xml"));

		// Act
		FinanceMoviment actualOutput = financeMovimentService.convert(xmlEntity);

		// Assert
		Assertions.assertNotNull(actualOutput);
	}
}