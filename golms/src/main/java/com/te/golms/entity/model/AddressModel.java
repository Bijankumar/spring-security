package com.te.golms.entity.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.golms.enums.AddressType;
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
public class AddressModel {
	private String doorNo;
	private String street;
	private String locality;
	private String city;
	private Integer pincode;
	private String landmark;
	@Enumerated(EnumType.STRING)
	private State state;
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
}
