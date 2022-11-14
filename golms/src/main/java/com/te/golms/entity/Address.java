package com.te.golms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity
@Table(name = "employee_address_info")
public class Address {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer addressId;

	@Column(name = "door_no")
	private String doorNo;

	@Column(name = "street")
	private String street;

	@Column(name = "locality")
	private String locality;

	@NotNull(message = "NULL data passed for city")
	@NotBlank(message = "BLANK data passes city")
	@Column(name = "city")
	private String city;

	@NotNull(message = "NULL data passed for pincode")
	@Column(name = "pincode")
	private Integer pincode;

	@NotNull(message = "NULL data passed for landmark")
	@NotBlank(message = "BLANK data passes landmark")
	@Column(name = "landmark")
	private String landmark;

	@Enumerated(EnumType.STRING)
	private State state;

	@NotNull(message = "NULL data passed for addressType")
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
}
