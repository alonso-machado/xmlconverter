package com.alonso.xmlconverter.mapper;

import com.alonso.xmlconverter.model.input.*;
import com.alonso.xmlconverter.model.output.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {

	@BeforeMapping
	protected void enrichEntity(Cliente entityXml, @MappingTarget Customer customer) {
		if (entityXml.getPagamentoPreferencial().equals(PagamentoEnum.B)) {
			customer.setPreferentialPayment(PaymentTypeEnum.T);
		} else {
			customer.setPreferentialPayment(PaymentTypeEnum.valueOf(entityXml.getPagamentoPreferencial().toString()));
		}
	}

	@Mapping(target = "name", source = "entityXml.nome")
	@Mapping(target="creationDate", source="entityXml.dataCadastro", dateFormat="yyyy-MM-dd")
	@Mapping(target = "birthdayDate", source="entityXml.dataNascimento", dateFormat="yyyy-MM-dd")
	@Mapping(target = "gender", source = "entityXml.sexo")
	@BeanMapping(builder = @Builder(disableBuilder = true))
	public abstract Customer toCustomer(Cliente entityXml);
}
