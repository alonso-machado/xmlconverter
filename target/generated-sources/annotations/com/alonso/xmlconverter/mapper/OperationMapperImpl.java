package com.alonso.xmlconverter.mapper;

import com.alonso.xmlconverter.model.input.Operacao;
import com.alonso.xmlconverter.model.output.Customer;
import com.alonso.xmlconverter.model.output.HasScheduleEnum;
import com.alonso.xmlconverter.model.output.Operation;
import com.alonso.xmlconverter.model.output.OperationTypeEnum;
import com.alonso.xmlconverter.model.output.PaymentTypeEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-04T17:37:01-0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class OperationMapperImpl extends OperationMapper {

    @Override
    public Operation toOperation(Operacao operacao) {
        if ( operacao == null ) {
            return null;
        }

        BigDecimal amountPaid = null;
        BigDecimal originalAmount = null;
        LocalDate dueDate = null;
        BigDecimal amountFee = null;
        Integer totalSchedules = null;
        Integer paidScheduleNumber = null;
        Integer id = null;

        if ( operacao.getValorPago() != null ) {
            amountPaid = operacao.getValorPago();
        }
        if ( operacao.getValorOriginal() != null ) {
            originalAmount = operacao.getValorOriginal();
        }
        if ( operacao.getDtVencimento() != null ) {
            dueDate = operacao.getDtVencimento();
        }
        if ( operacao.getValorJuros() != null ) {
            amountFee = operacao.getValorJuros();
        }
        if ( operacao.getTotalParcelas() != null ) {
            totalSchedules = operacao.getTotalParcelas();
        }
        if ( operacao.getParcelaPaga() != null ) {
            paidScheduleNumber = operacao.getParcelaPaga();
        }
        if ( operacao.getId() != null ) {
            id = operacao.getId();
        }

        OperationTypeEnum operationType = null;
        PaymentTypeEnum paymentType = null;
        HasScheduleEnum hasSchedules = null;
        Customer customer = null;

        Operation operation = new Operation( id, operationType, paymentType, amountPaid, originalAmount, dueDate, amountFee, hasSchedules, totalSchedules, paidScheduleNumber, customer );

        enrichJSON( operacao, operation );

        return operation;
    }
}
