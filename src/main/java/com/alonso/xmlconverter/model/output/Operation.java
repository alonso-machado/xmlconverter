package com.alonso.xmlconverter.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class Operation implements Serializable {
	private Integer id;
	private OperationTypeEnum operationType;
	private PaymentTypeEnum paymentType;
	private BigDecimal amountPaid;
	private BigDecimal originalAmount;
	private LocalDate dueDate;
	private BigDecimal amountFee;
	private HasScheduleEnum hasSchedules;
	private Integer totalSchedules;
	private Integer paidScheduleNumber;
	private Customer customer;

}