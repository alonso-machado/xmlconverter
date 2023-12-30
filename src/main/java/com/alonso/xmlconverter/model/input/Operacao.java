package com.alonso.xmlconverter.model.input;

import com.alonso.xmlconverter.adapters.BigDecimalAdapter;
import com.alonso.xmlconverter.adapters.LocalDateXmlAdapter;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class Operacao implements Serializable {

	@XmlElement(name = "ID")
	private Integer id;
	@XmlElement(name = "TIPO_OPERACAO")
	private TipoOperacaoEnum tipoOperacao;
	@XmlElement(name = "FORMA_PAGAMENTO")
	private PagamentoEnum formaPagamento;
	@XmlElement(name = "VALOR_PAGO")
	@XmlJavaTypeAdapter(BigDecimalAdapter.class)
	private BigDecimal valorPago;
	@XmlElement(name = "VALOR_ORIGINAL")
	@XmlJavaTypeAdapter(BigDecimalAdapter.class)
	private BigDecimal valorOriginal;
	@XmlElement(name = "DT_VENCIMENTO")
	@XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
	private LocalDate dtVencimento;
	@XmlElement(name = "VALOR_JUROS")
	@XmlJavaTypeAdapter(BigDecimalAdapter.class)
	private BigDecimal valorJuros;
	@XmlElement(name = "POSSUI_PARCELAMENTO")
	private PossuiParcelamentosEnum possuiParcelamento;
	@XmlElement(name = "TOTAL_PARCELAS")
	private Integer totalParcelas;
	@XmlElement(name = "PARCELA_PAGA")
	private Integer parcelaPaga;
	@XmlElement(name = "CLIENTE")
	private Cliente cliente;

}
