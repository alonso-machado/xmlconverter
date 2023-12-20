package com.alonso.xmlconverter.unit.mapper;

import com.alonso.xmlconverter.mapper.MovimentMapper;
import com.alonso.xmlconverter.mapper.MovimentMapperImpl;
import com.alonso.xmlconverter.model.input.*;
import com.alonso.xmlconverter.model.output.Moviment;
import com.alonso.xmlconverter.model.output.OperationTypeEnum;
import com.alonso.xmlconverter.model.output.PaymentTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class MovimentMapperTest {

	private MovimentMapper movimentMapper;

	@BeforeEach
	public void setUp() {
		movimentMapper = new MovimentMapperImpl();
	}

	@Test
	void whenMapMovimentFromXMLOperacaoL_PagamentoB_thenGetExpectedResult_OperationS_PaymentT() {
		// Arrange
		Double random = Math.abs(Math.random() * 101);
		LocalDate testDate = LocalDate.of(2023, 12, 18);
		BigDecimal valor = BigDecimal.valueOf(random).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal valor10 = BigDecimal.valueOf(random * 10).setScale(2, BigDecimal.ROUND_HALF_UP);
		Movimento mov = new Movimento();
		mov.setOperacao(Operacao.builder().id(random.intValue()).tipoOperacao(TipoOperacaoEnum.L).formaPagamento(PagamentoEnum.B)
				.dtVencimento(testDate).totalParcelas(10).parcelaPaga(1).possuiParcelamento(PossuiParcelamentosEnum.S)
				.valorPago(valor).valorOriginal(valor10).valorJuros(valor).build());


		// Act - Call the method being tested
		Moviment moviment = movimentMapper.toMoviment(mov);

		// Assert - Perform assertions to check the result
		Assertions.assertNotNull(moviment);
		Assertions.assertNotNull(moviment.getOperation());
		Assertions.assertEquals(random.intValue(), moviment.getOperation().getId());
		Assertions.assertEquals(testDate, moviment.getOperation().getDueDate());
		Assertions.assertEquals(OperationTypeEnum.S, moviment.getOperation().getOperationType());
		Assertions.assertEquals(PaymentTypeEnum.T, moviment.getOperation().getPaymentType());
	}
}