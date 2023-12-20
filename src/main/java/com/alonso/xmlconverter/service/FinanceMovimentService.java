package com.alonso.xmlconverter.service;


import com.alonso.xmlconverter.mapper.FinanceMovimentMapper;
import com.alonso.xmlconverter.model.input.MovimentacaoFinanceira;
import com.alonso.xmlconverter.model.output.FinanceMoviment;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class FinanceMovimentService {

	private final FinanceMovimentMapper financeMovimentMapper;

	public FinanceMoviment convert(MovimentacaoFinanceira xmlEntity) {
		FinanceMoviment fromMapper = financeMovimentMapper.toFinanceMoviment(xmlEntity);
		log.info("Converting XML to JSON{}", xmlEntity, fromMapper);
		return fromMapper;
	}

}
