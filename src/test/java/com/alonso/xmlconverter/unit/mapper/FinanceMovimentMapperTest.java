package com.alonso.xmlconverter.unit.mapper;

import com.alonso.xmlconverter.mapper.FinanceMovimentMapper;
import com.alonso.xmlconverter.mapper.FinanceMovimentMapperImpl;
import com.alonso.xmlconverter.model.input.*;
import com.alonso.xmlconverter.model.output.FinanceMoviment;
import com.alonso.xmlconverter.model.output.OperationTypeEnum;
import com.alonso.xmlconverter.model.output.PaymentTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class FinanceMovimentMapperTest {

	private FinanceMovimentMapper financeMovimentMapper;

	@BeforeEach
	public void setUp() {
		financeMovimentMapper = new FinanceMovimentMapperImpl();
	}

	@Test
	void whenMapFinanceMovimentFromXMLOperacaoL_PagamentoB_thenGetExpectedResult_OperationS_PaymentT() {
		// Arrange
		Double random = Math.abs(Math.random() * 101);
		LocalDate testDate = LocalDate.of(2023, 12, 18);
		BigDecimal valor = BigDecimal.valueOf(random).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal valor10 = BigDecimal.valueOf(random * 10).setScale(2, BigDecimal.ROUND_HALF_UP);
		MovimentacaoFinanceira xmlEntity = new MovimentacaoFinanceira();
		Movimento mov = new Movimento();
		mov.setOperacao(Operacao.builder().id(random.intValue()).tipoOperacao(TipoOperacaoEnum.L).formaPagamento(PagamentoEnum.B)
				.dtVencimento(testDate).totalParcelas(10).parcelaPaga(1).possuiParcelamento(PossuiParcelamentosEnum.S)
				.valorPago(valor).valorOriginal(valor10).valorJuros(valor).build());
		xmlEntity.setMovimento(mov);

		// Act - Call the method being tested
		FinanceMoviment financeMoviment = financeMovimentMapper.toFinanceMoviment(xmlEntity);

		// Assert - Perform assertions to check the result
		Assertions.assertNotNull(financeMoviment);
		Assertions.assertNotNull(financeMoviment.getMoviment());
		Assertions.assertNotNull(financeMoviment.getMoviment().getOperation());
		Assertions.assertEquals(random.intValue(), financeMoviment.getMoviment().getOperation().getId());
		Assertions.assertEquals(testDate, financeMoviment.getMoviment().getOperation().getDueDate());
		Assertions.assertEquals(OperationTypeEnum.S, financeMoviment.getMoviment().getOperation().getOperationType());
		Assertions.assertEquals(PaymentTypeEnum.T, financeMoviment.getMoviment().getOperation().getPaymentType());
	}
}