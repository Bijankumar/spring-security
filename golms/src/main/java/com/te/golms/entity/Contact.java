package com.te.golms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.te.golms.enums.ContactType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_contact_info")
public class Contact {
	@Id
	@Column(name = "contact_number")
	private String contactNumber;

	@Enumerated(EnumType.STRING)
	private ContactType contactType;
}
