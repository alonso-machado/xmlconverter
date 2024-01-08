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
	@BeanMapping(builder = @Builder(disableBuilder = true),	nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	public abstract Operation toOperation(Operacao operacao);

	//NullValuePropertyMappingStrategy.IGNORE to ignore null values

	/*

	public Cliente mapClienteFromCustomer(Customer cus) {
		return Mappers.getMapper(CustomerMapper.class).toCliente(cus);
	}

	@BeforeMapping
	protected void enrichEntity(Operation operationJson, @MappingTarget Operacao operacao) {
		if (operationJson.getOperationType().equals(OperationTypeEnum.S)) {
			operacao.setTipoOperacao(TipoOperacaoEnum.L);
		} else {
			operacao.setTipoOperacao(TipoOperacaoEnum.valueOf(operationJson.getOperationType().toString()));
		}
		if (operationJson.getPaymentType().equals(PaymentTypeEnum.T)) {
			operacao.setFormaPagamento(PagamentoEnum.B);
		} else {
			operationJson.setPaymentType(PaymentTypeEnum.valueOf(operacao.getFormaPagamento().toString()));
		}
		if (operationJson.getHasSchedules().equals(HasScheduleEnum.Y)) {
			operacao.setPossuiParcelamento(PossuiParcelamentosEnum.S);
		} else {
			operacao.setPossuiParcelamento(PossuiParcelamentosEnum.N);
		}
		if( operationJson.getCustomer() != null){
			operacao.setCliente( mapClienteFromCustomer(operationJson.getCustomer()) );
		}

	}

	@Mapping(target = "valorPago", source = "operationJson.amountPaid")
	@Mapping(target = "valorOriginal", source = "operationJson.originalAmount")
	@Mapping(target = "dtVencimento", source = "operationJson.dueDate", dateFormat = "dd/MM/yyyy")
	@Mapping(target = "valorJuros", source = "operationJson.amountFee")
	@Mapping(target = "totalParcelas", source = "operationJson.totalSchedules")
	@Mapping(target = "parcelaPaga", source = "operationJson.paidScheduleNumber")
	@BeanMapping(builder = @Builder(disableBuilder = true))
	public abstract Operacao toOperacaoEntity(Operation operationJson);
	*/
}
