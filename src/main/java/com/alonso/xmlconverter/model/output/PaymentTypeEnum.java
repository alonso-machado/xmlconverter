package com.alonso.xmlconverter.model.output;

//XML - "FORMA DE PAGAMENTO": B = BOLETO | D = DEBITO EM CONTA | C = CARTAO | P = PIX

//JSON - "paymentType": T = Ticket | D = Debit | C = Credit Card | P = PIX
public enum PaymentTypeEnum {
	T, D, C, P
}
