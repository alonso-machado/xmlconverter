package com.alonso.xmlconverter.mapper;

import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import com.alonso.xmlconverter.model.input.Movimento;
import com.alonso.xmlconverter.model.output.FinanceMoviment;
import com.alonso.xmlconverter.model.output.Moviment;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class FinanceMovimentMapper {

	public Moviment mapMovimentFromMovimento(Movimento movimento) {
		return Mappers.getMapper(MovimentMapper.class).toMoviment(movimento);
	}

	@BeforeMapping
	protected void enrichJSON(MovimentacaoFinanceira movimentacaoFinanceira, @MappingTarget FinanceMoviment financeMoviment){
		financeMoviment.setMoviment(mapMovimentFromMovimento(movimentacaoFinanceira.getMovimento()));
	}

	@BeanMapping(builder = @Builder(disableBuilder = true))
	public abstract FinanceMoviment toFinanceMoviment(MovimentacaoFinanceira xmlEntity);

}