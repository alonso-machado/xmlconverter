package com.alonso.xmlconverter.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement(name = "MOVIMENTACAO_FINANCEIRA")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovimentacaoFinanceira implements Serializable {
	@XmlElement(name = "MOVIMENTO")
	private Movimento movimento;
}
