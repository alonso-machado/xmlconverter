package com.alonso.xmlconverter.mapper;

import com.alonso.xmlconverter.model.GenderEnum;
import com.alonso.xmlconverter.model.input.Cliente;
import com.alonso.xmlconverter.model.output.Customer;
import com.alonso.xmlconverter.model.output.PaymentTypeEnum;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-20T12:31:30-0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl extends CustomerMapper {

    @Override
    public Customer toCustomer(Cliente entityXml) {
        if ( entityXml == null ) {
            return null;
        }

        String name = null;
        LocalDate creationDate = null;
        LocalDate birthdayDate = null;
        GenderEnum gender = null;
        Integer id = null;

        name = entityXml.getNome();
        creationDate = entityXml.getDataCadastro();
        birthdayDate = entityXml.getDataNascimento();
        gender = entityXml.getSexo();
        id = entityXml.getId();

        PaymentTypeEnum preferentialPayment = null;

        Customer customer = new Customer( id, name, creationDate, birthdayDate, gender, preferentialPayment );

        enrichEntity( entityXml, customer );

        return customer;
    }
}
