package com.te.golms.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetModel {
	private String empId;
	private String oldPassword;
	private String newPassword;
}
