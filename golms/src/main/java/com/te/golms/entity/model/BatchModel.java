package com.te.golms.entity.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.golms.enums.BatchStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchModel {
	private String batchName;
	private String mentorId;
	@Enumerated(EnumType.STRING)
	private Set<TechnologyModel> technologies;
	private LocalDate batchStartDate;
	private LocalDate batchEndDate;
	@Enumerated(EnumType.STRING)
	private BatchStatus batchStatus;
}
