package com.te.golms.entity.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.golms.enums.ContactType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactModel {
	private String contactNumber;
	@Enumerated(EnumType.STRING)
	private ContactType contactType;
}
