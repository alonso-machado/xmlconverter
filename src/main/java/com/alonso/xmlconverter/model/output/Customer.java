package com.alonso.xmlconverter.model.output;

import com.alonso.xmlconverter.model.GenderEnum;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class Customer implements Serializable {
	private Integer id;
	private String name;
	private LocalDate creationDate;
	private LocalDate birthdayDate;
	private GenderEnum gender;
	private PaymentTypeEnum preferentialPayment;
}