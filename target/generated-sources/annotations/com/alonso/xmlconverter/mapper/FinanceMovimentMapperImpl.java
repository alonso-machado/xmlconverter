package com.alonso.xmlconverter.mapper;

import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import com.alonso.xmlconverter.model.output.FinanceMoviment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-20T12:31:30-0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class FinanceMovimentMapperImpl extends FinanceMovimentMapper {

    @Override
    public FinanceMoviment toFinanceMoviment(MovimentacaoFinanceira xmlEntity) {
        if ( xmlEntity == null ) {
            return null;
        }

        FinanceMoviment financeMoviment = new FinanceMoviment();

        enrichJSON( xmlEntity, financeMoviment );

        return financeMoviment;
    }
}
