package com.alonso.xmlconverter.model.input;

import com.alonso.xmlconverter.adapters.LocalDateXmlAdapter;
import com.alonso.xmlconverter.model.GenderEnum;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class Cliente implements Serializable {
	@XmlElement(name = "ID")
	private Integer id;
	@XmlElement(name = "NOME")
	private String nome;
	@XmlElement(name = "DT_CADASTRO")
	@XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
	private LocalDate dataCadastro;
	@XmlElement(name = "DT_NASCIMENTO")
	@XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
	private LocalDate dataNascimento;
	@XmlElement(name = "SEXO")
	private GenderEnum sexo;
	@XmlElement(name = "PAGAMENTO_PREFERENCIAL")
	private PagamentoEnum pagamentoPreferencial;
}
