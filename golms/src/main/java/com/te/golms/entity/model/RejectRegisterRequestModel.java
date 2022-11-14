package com.te.golms.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RejectRegisterRequestModel {
	private Integer requestId;
	private String rejectReason;
}
