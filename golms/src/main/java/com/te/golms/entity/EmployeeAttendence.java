package com.te.golms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"attendenceId"})
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAttendence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendence_id")
	private Integer attendenceId;

	@NotNull(message = "NULL data passed for attendenceDate")
	@Column(name = "attendence_date")
	private LocalDate attendenceDate;

	@NotNull(message = "NULL data passed for attendenceMorning")
	@Column(name = "attendence_morning")
	private Boolean attendenceMorning;

	@NotNull(message = "NULL data passed for attendenceNoon")
	@Column(name = "attendence_noon")
	private Boolean attendenceNoon;
}
