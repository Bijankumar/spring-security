package com.te.golms.entity.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendenceInfoModel {
	private String empId;
	private LocalDate attendenceDate;
	private Boolean attendenceMorning;
	private Boolean attendenceNoon;
}
