package com.te.golms.entity.model;



import com.te.golms.enums.EmployeeStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpStatusModel {
	private String empId;
	private EmployeeStatus empStatus;
}
