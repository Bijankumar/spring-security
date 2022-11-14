package com.te.spring.boot.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "department_table")
public class Department implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private Long departmentId;

	/*
	 * Some other validations!
	 * 
	 * @Length(max = 5, min = 1)
	 * 
	 * @Size(max = 10, min = 0)
	 * 
	 * @Email
	 * 
	 * @Positive
	 * 
	 * @Negative
	 * 
	 * @PositiveOrZero
	 * 
	 * @NegativeOrZero
	 * 
	 * @Future
	 * 
	 * @FutureOrPresent
	 * 
	 * @Past
	 * 
	 * @PastOrPresent
	 */
	@Column(name = "department_name")
	@NotBlank(message = "Department name is mandetory!")
	private String departmentName;

	@Column(name = "department_address")
	private String departmentAddress;

}
