package com.te.golms.entity.model;

import com.te.golms.enums.BatchStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchStatusModel {
	private String batchName;
	private BatchStatus batchStatus;
}
