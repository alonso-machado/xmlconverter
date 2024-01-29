package com.alonso.xmlconverter.mapper;


import com.alonso.xmlconverter.model.input.*;
import com.alonso.xmlconverter.model.output.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class OperationMapper {


	public Customer mapCustomerFromCliente(Cliente cli) {
		return Mappers.getMapper(CustomerMapper.class).toCustomer(cli);
	}

	@BeforeMapping
	protected void enrichJSON(Operacao operacao, @MappingTarget Operation operation) {
		if (operacao.getTipoOperacao().equals(TipoOperacaoEnum.L)) {
			operation.setOperationType(OperationTypeEnum.S);
		} else {
			operation.setOperationType(OperationTypeEnum.valueOf(operacao.getTipoOperacao().toString()));
		}
		if (operacao.getFormaPagamento().equals(PagamentoEnum.B)) {
			operation.setPaymentType(PaymentTypeEnum.T);
		} else {
			operation.setPaymentType(PaymentTypeEnum.valueOf(operacao.getFormaPagamento().toString()));
		}
		if (operacao.getPossuiParcelamento().equals(PossuiParcelamentosEnum.S)) {
			operation.setHasSchedules(HasScheduleEnum.Y);
		} else {
			operation.setHasSchedules(HasScheduleEnum.N);
		}
		if( operacao.getCliente() != null){
			operation.setCustomer( mapCustomerFromCliente(operacao.getCliente()) );
		}

	}

	@Mapping(target = "amountPaid", source = "operacao.valorPago")
	@Mapping(target = "originalAmount", source = "operacao.valorOriginal")
	@Mapping(target = "dueDate", source = "operacao.dtVencimento", dateFormat = "yyyy-MM-dd")
	@Mapping(target = "amountFee", source = "operacao.valorJuros")
	@Mapping(target = "totalSchedules", source = "operacao.totalParcelas")
	@Mapping(target = "paidScheduleNumber", source = "operacao.parcelaPaga")
	@BeanMapping(builder = @Builder(disableBuilder = true),
			subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
	public abstract Operation toOperation(Operacao operacao);

	//NullValueCheckStrategy.ALWAYS to Gracefully Accept the XML without some components and map to NULL
	//NullValuePropertyMappingStrategy.IGNORE to ignore null values
}
