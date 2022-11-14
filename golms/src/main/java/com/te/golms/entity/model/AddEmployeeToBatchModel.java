package com.te.golms.entity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddEmployeeToBatchModel {
	private String empId;
	private String batchName;
	private Integer batchId;
	private Integer requestId;
}
