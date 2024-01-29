package com.alonso.xmlconverter.mapper;

import com.alonso.xmlconverter.model.input.Movimento;
import com.alonso.xmlconverter.model.input.Operacao;
import com.alonso.xmlconverter.model.output.Moviment;
import com.alonso.xmlconverter.model.output.Operation;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class MovimentMapper {

	public Operation mapOperationFromOperacao(Operacao operacao) {
		return Mappers.getMapper(OperationMapper.class).toOperation(operacao);
	}

	@BeforeMapping
	protected void enrichJSON(Movimento movimento, @MappingTarget Moviment moviment){
		moviment.setOperation(mapOperationFromOperacao(movimento.getOperacao()));
	}

	@BeanMapping(builder = @Builder(disableBuilder = true),
			subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
	public abstract Moviment toMoviment(Movimento xmlEntity);

}