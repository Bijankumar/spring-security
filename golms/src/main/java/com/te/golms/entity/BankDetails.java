package com.te.golms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.te.golms.enums.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_bank_details_info")
public class BankDetails {
	@Id
	@Column(name = "account_no")
	private Long accountNo;

	@NotNull(message = "NULL data passed for bankName")
	@NotBlank(message = "BLANK data passes bankName")
	@Column(name = "bank_name")
	private String bankName;

	@NotNull(message = "NULL data passed for accountType")
	@NotBlank(message = "BLANK data passes accountType")
	@Column(name = "account_type")
	private String accountType;

	@NotNull(message = "NULL data passed for ifsc")
	@NotBlank(message = "BLANK data passes ifsc")
	@Column(name = "bank_ifsc")
	private String ifsc;

	@NotNull(message = "NULL data passed for branch")
	@NotBlank(message = "BLANK data passes branch")
	@Column(name = "bank_branch")
	private String branch;

	@Enumerated(EnumType.STRING)
	private State state;
}
