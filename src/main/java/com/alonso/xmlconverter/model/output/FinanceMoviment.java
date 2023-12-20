package com.alonso.xmlconverter.model.output;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FinanceMoviment implements Serializable {
	private Moviment moviment;
}
