package com.alonso.xmlconverter.service;


import com.alonso.xmlconverter.mapper.FinanceMovimentMapper;
import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import com.alonso.xmlconverter.model.output.FinanceMoviment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FinanceMovimentService {

	private final FinanceMovimentMapper financeMovimentMapper;

	public FinanceMoviment convert(MovimentacaoFinanceira xmlEntity) throws NullPointerException {
		return financeMovimentMapper.toFinanceMoviment(xmlEntity);
	}

}
