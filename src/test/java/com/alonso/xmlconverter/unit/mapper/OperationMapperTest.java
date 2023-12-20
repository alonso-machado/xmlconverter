package com.alonso.xmlconverter.unit.mapper;

import com.alonso.xmlconverter.mapper.OperationMapper;
import com.alonso.xmlconverter.mapper.OperationMapperImpl;
import com.alonso.xmlconverter.model.GenderEnum;
import com.alonso.xmlconverter.model.input.*;
import com.alonso.xmlconverter.model.output.Operation;
import com.alonso.xmlconverter.model.output.OperationTypeEnum;
import com.alonso.xmlconverter.model.output.PaymentTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class OperationMapperTest {

	private OperationMapper operationMapper;

	@BeforeEach
	public void setUp() {
		operationMapper = new OperationMapperImpl();
	}

	@Test
	 void whenMapFromXMLOperacaoL_PagamentoB_thenGetExpectedResult_OperationS_PaymentT() {
		// Arrange
		Double random = Math.abs(Math.random() * 100);
		LocalDate testDate = LocalDate.of(2023, 12, 18);
		BigDecimal valor = BigDecimal.valueOf(random).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal valor10 = BigDecimal.valueOf(random*10).setScale(2, BigDecimal.ROUND_HALF_UP);
		Operacao op = Operacao.builder().id(random.intValue()).tipoOperacao(TipoOperacaoEnum.L).formaPagamento(PagamentoEnum.B)
				.dtVencimento(testDate)	.totalParcelas(5).parcelaPaga(2).possuiParcelamento(PossuiParcelamentosEnum.S)
				.valorPago(valor).valorOriginal(valor10).valorJuros(valor).build();

		// Act - Call the method being tested
		Operation operation = operationMapper.toOperation(op);

		// Assert - Perform assertions to check the result
		Assertions.assertNotNull(operation);
		Assertions.assertEquals(random.intValue(), operation.getId());
		Assertions.assertEquals(testDate, operation.getDueDate());
		Assertions.assertEquals(valor, operation.getAmountPaid());
		Assertions.assertEquals(valor10, operation.getOriginalAmount());
		Assertions.assertEquals(OperationTypeEnum.S, operation.getOperationType());
		Assertions.assertEquals(PaymentTypeEnum.T, operation.getPaymentType());
	}

	@Test
	void whenMapFromXMLOperacaoR_PagamentoP_thenConvertedResult_OperationR_PaymentP() {
		// Arrange
		Double random = Math.abs(Math.random() * 1000);
		LocalDate testDate = LocalDate.of(2023, 11, 12);
		BigDecimal valor = BigDecimal.valueOf(random).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal valor10 = BigDecimal.valueOf(random*10).setScale(2, BigDecimal.ROUND_HALF_UP);
		Operacao op = Operacao.builder().id(random.intValue()).tipoOperacao(TipoOperacaoEnum.R).formaPagamento(PagamentoEnum.P)
				.dtVencimento(testDate).totalParcelas(10).parcelaPaga(1).possuiParcelamento(PossuiParcelamentosEnum.S)
				.valorPago(valor).valorOriginal(valor10).valorJuros(valor).build();

		// Act - Call the method being tested
		Operation operation = operationMapper.toOperation(op);

		// Assert - Perform assertions to check the result
		Assertions.assertNotNull(operation);
		Assertions.assertEquals(random.intValue(), operation.getId());
		Assertions.assertEquals(testDate, operation.getDueDate());
		Assertions.assertEquals(valor, operation.getAmountPaid());
		Assertions.assertEquals(valor10, operation.getOriginalAmount());
		Assertions.assertEquals(OperationTypeEnum.R, operation.getOperationType());
		Assertions.assertEquals(PaymentTypeEnum.P, operation.getPaymentType());
	}

	@Test
	void whenMapFromXMLOperacaoL_PagamentoB_WithCLIENTE_thenGetExpectedResult_OperationS_PaymentT() {
		// Arrange
		Double random = Math.abs(Math.random() * 21);
		LocalDate testDate = LocalDate.of(2023, 12, 18);
		BigDecimal valor = BigDecimal.valueOf(random).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal valor10 = BigDecimal.valueOf(random*10).setScale(2, BigDecimal.ROUND_HALF_UP);
		Cliente cli = Cliente.builder().dataCadastro(testDate).nome("Alonso").sexo(GenderEnum.M).pagamentoPreferencial(PagamentoEnum.C).dataNascimento(testDate.minusYears(25)).build();
		Operacao op = Operacao.builder().id(random.intValue()).tipoOperacao(TipoOperacaoEnum.L).formaPagamento(PagamentoEnum.B)
				.dtVencimento(testDate)	.totalParcelas(5).parcelaPaga(2).possuiParcelamento(PossuiParcelamentosEnum.S)
				.cliente(cli).valorPago(valor).valorOriginal(valor10).valorJuros(valor).build();

		// Act - Call the method being tested
		Operation operation = operationMapper.toOperation(op);

		// Assert - Perform assertions to check the result
		Assertions.assertNotNull(operation);
		Assertions.assertEquals(random.intValue(), operation.getId());
		Assertions.assertEquals(testDate, operation.getDueDate());
		Assertions.assertEquals(valor, operation.getAmountPaid());
		Assertions.assertEquals(valor10, operation.getOriginalAmount());
		Assertions.assertEquals(OperationTypeEnum.S, operation.getOperationType());
		Assertions.assertEquals(PaymentTypeEnum.T, operation.getPaymentType());

		// Assert the Customer inside Operation
		Assertions.assertNotNull(operation.getCustomer());
		Assertions.assertEquals(cli.getId(), operation.getCustomer().getId());
		Assertions.assertEquals(cli.getNome(), operation.getCustomer().getName());
		Assertions.assertEquals(cli.getSexo(), operation.getCustomer().getGender());
		Assertions.assertEquals(cli.getDataNascimento(), operation.getCustomer().getBirthdayDate());
		Assertions.assertEquals(cli.getDataCadastro(), operation.getCustomer().getCreationDate());
	}
}