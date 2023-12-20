package com.alonso.xmlconverter.integration.service;

import com.alonso.xmlconverter.mapper.FinanceMovimentMapper;
import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import com.alonso.xmlconverter.model.output.FinanceMoviment;
import com.alonso.xmlconverter.model.output.OperationTypeEnum;
import com.alonso.xmlconverter.model.output.PaymentTypeEnum;
import com.alonso.xmlconverter.service.FinanceMovimentService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class FinanceMovimentServiceIntegrationTest {

	private FinanceMovimentService financeMovimentService;

	@Autowired
	private FinanceMovimentMapper financeMovimentMapper;

	@BeforeEach
	void setUp() {
		financeMovimentService = new FinanceMovimentService(financeMovimentMapper);
	}



	@Test
	void whenConvert_thenReturnExpected() throws JAXBException, FileNotFoundException {
		//Arrange
		JAXBContext context = JAXBContext.newInstance(MovimentacaoFinanceira.class);
		MovimentacaoFinanceira xmlEntity = (MovimentacaoFinanceira) context.createUnmarshaller().unmarshal(new FileReader("src/test/resources/inputExample.xml"));

		// Act
		FinanceMoviment actualOutput = financeMovimentService.convert(xmlEntity);

		// Assert
		Assertions.assertNotNull(actualOutput);
		Assertions.assertNotNull(actualOutput.getMoviment());
		Assertions.assertNotNull(actualOutput.getMoviment().getOperation());
		Assertions.assertEquals(1234, actualOutput.getMoviment().getOperation().getId());
		Assertions.assertEquals(BigDecimal.valueOf(154.50), actualOutput.getMoviment().getOperation().getAmountPaid());
		Assertions.assertEquals(LocalDate.parse("2023-05-01"), actualOutput.getMoviment().getOperation().getDueDate());
		Assertions.assertEquals(10, actualOutput.getMoviment().getOperation().getTotalSchedules());
		Assertions.assertEquals(OperationTypeEnum.P, actualOutput.getMoviment().getOperation().getOperationType());
		Assertions.assertEquals(PaymentTypeEnum.T, actualOutput.getMoviment().getOperation().getPaymentType()); // B in inputExample need to be converted to in output

		Assertions.assertNotNull(actualOutput.getMoviment().getOperation().getCustomer());
		Assertions.assertEquals(123456, actualOutput.getMoviment().getOperation().getCustomer().getId());
		Assertions.assertEquals("Maria Silva", actualOutput.getMoviment().getOperation().getCustomer().getName());
	}
}