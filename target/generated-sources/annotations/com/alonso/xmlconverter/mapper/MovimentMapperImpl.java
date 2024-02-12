package com.alonso.xmlconverter.mapper;

import com.alonso.xmlconverter.model.input.Movimento;
import com.alonso.xmlconverter.model.output.Moviment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-12T11:42:58-0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class MovimentMapperImpl extends MovimentMapper {

    @Override
    public Moviment toMoviment(Movimento xmlEntity) {
        if ( xmlEntity == null ) {
            return null;
        }

        Moviment moviment = new Moviment();

        enrichJSON( xmlEntity, moviment );

        return moviment;
    }
}
