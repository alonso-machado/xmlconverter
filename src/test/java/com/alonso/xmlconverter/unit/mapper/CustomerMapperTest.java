package com.alonso.xmlconverter.unit.mapper;

import com.alonso.xmlconverter.mapper.CustomerMapper;
import com.alonso.xmlconverter.mapper.CustomerMapperImpl;
import com.alonso.xmlconverter.model.GenderEnum;
import com.alonso.xmlconverter.model.input.*;
import com.alonso.xmlconverter.model.output.Customer;
import com.alonso.xmlconverter.model.output.Operation;
import com.alonso.xmlconverter.model.output.OperationTypeEnum;
import com.alonso.xmlconverter.model.output.PaymentTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CustomerMapperTest {

	private CustomerMapper customerMapper;

	@BeforeEach
	public void setUp() {
		customerMapper = new CustomerMapperImpl();
	}

	@Test
	 void whenMapFromXMLPagamentoB_thenGetExpectedResult_PaymentT() {
		// Arrange
		Double random = Math.abs(Math.random() * 10);
		LocalDate testDate = LocalDate.of(2023, 12, 18);
		Cliente cli = Cliente.builder().dataCadastro(testDate).nome("Alonso").id(random.intValue()).sexo(GenderEnum.M).pagamentoPreferencial(PagamentoEnum.B).dataNascimento(testDate.minusYears(25)).build();

		// Act
		Customer cus = customerMapper.toCustomer(cli);

		// Assert - Perform assertions to check the result
		Assertions.assertNotNull(cus);
		Assertions.assertEquals(random.intValue(), cus.getId());
		Assertions.assertEquals(testDate.minusYears(25), cus.getBirthdayDate());
		Assertions.assertEquals(testDate, cus.getCreationDate());
		Assertions.assertEquals(PaymentTypeEnum.T, cus.getPreferentialPayment());
	}

}