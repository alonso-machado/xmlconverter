package com.alonso.xmlconverter.model.output;

//XML - "TIPO OPERACAO": P = PAGAMENTO | R = REEMBOLSO | L = LIQUIDAR

//JSON - "operationType": P = Payment | R = Reimbursement | S = Settlement
public enum OperationTypeEnum {
	P, R, S
}
