package com.te.golms.entity.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.golms.enums.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankDetailsModel {
	private Long accountNo;
	private String bankName;
	private String accountType;
	private String ifsc;
	private String branch;
	@Enumerated(EnumType.STRING)
	private State state;
}
