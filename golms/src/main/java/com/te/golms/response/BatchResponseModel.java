package com.te.golms.response;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.golms.entity.model.EmployeeModel;
import com.te.golms.entity.model.MentorModel;
import com.te.golms.entity.model.TechnologyModel;
import com.te.golms.enums.BatchStatus;
import com.te.golms.enums.BatchStrength;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchResponseModel {
	private String batchName;
	private LocalDate batchStartDate;
	private LocalDate batchEndDate;
	private Set<TechnologyModel> technologies = new HashSet<>();
	private Map<BatchStrength, Integer> batchStrength = new EnumMap<>(BatchStrength.class);
	@Enumerated(EnumType.STRING)
	private BatchStatus batchStatus;
	private MentorModel batchMentor;
	private List<EmployeeModel> employees;
}
