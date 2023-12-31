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
    date = "2023-12-30T20:12:19-0400",
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

        amountPaid = operacao.getValorPago();
        originalAmount = operacao.getValorOriginal();
        dueDate = operacao.getDtVencimento();
        amountFee = operacao.getValorJuros();
        totalSchedules = operacao.getTotalParcelas();
        paidScheduleNumber = operacao.getParcelaPaga();
        id = operacao.getId();

        OperationTypeEnum operationType = null;
        PaymentTypeEnum paymentType = null;
        HasScheduleEnum hasSchedules = null;
        Customer customer = null;

        Operation operation = new Operation( id, operationType, paymentType, amountPaid, originalAmount, dueDate, amountFee, hasSchedules, totalSchedules, paidScheduleNumber, customer );

        enrichJSON( operacao, operation );

        return operation;
    }
}
