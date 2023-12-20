package com.alonso.xmlconverter.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Movimento implements Serializable {
	@XmlElement(name = "OPERACAO")
	private Operacao operacao;
}
