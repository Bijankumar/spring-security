package com.te.golms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.te.golms.enums.BloodGroup;
import com.te.golms.enums.Degree;
import com.te.golms.enums.EmployeeStatus;
import com.te.golms.enums.Gender;
import com.te.golms.enums.Nationality;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_primary_info")
public class Employee {
	@Id
	@Column(name = "emp_id")
	private String empId;

	@NotNull(message = "NULL data passed for empName")
	@NotBlank(message = "BLANK data passes empName")
	@Size(min = 2, max = 100)
	@Column(name = "emp_name")
	private String empName;

	@NotNull
	@NotBlank
	@Email(message = "Email is not valid")
	@Column(name = "emp_email", unique = true)
	private String email;

	@NotNull(message = "NULL data passed for dateOfBirth")
	@Column(name = "emp_dob")
	private LocalDate dateOfBirth;

	@NotNull(message = "NULL data passed for dateOfJoining")
	@Column(name = "emp_doj")
	private LocalDate dateOfJoining;

	@NotNull(message = "NULL data passed for designation")
	@NotBlank(message = "BLANK data passes designation")
	@Column(name = "designation")
	private String designation;

	@Column(name = "last_login")
	private LocalDateTime lastLogin = null;

	@Column(name = "status_reason")
	private String statusReason;

	@NotNull(message = "NULL data passed for nationality")
	@Enumerated(EnumType.STRING)
	private Nationality nationality;

	@NotNull(message = "NULL data passed for gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotNull(message = "NULL data passed for bloodGroup")
	@Enumerated(EnumType.STRING)
	private BloodGroup bloodGroup;

	@NotNull(message = "NULL data passed for degree")
	@Enumerated(EnumType.STRING)
	private Degree degree;

	@NotNull(message = "NULL data passed for status")
	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;

	@OneToMany(cascade = CascadeType.ALL)
	private List<EmployeeAttendence> employeeAttendence;

	@ManyToOne(cascade = CascadeType.ALL)
	private Batch inBatch;

	// @NotNull(message = "NULL data passed for secondaryInfo")
	@OneToOne(cascade = CascadeType.ALL)
	private SecondaryInfo secondaryInfo;

	@NotNull(message = "NULL data passed for educationDetails")
	@OneToMany(cascade = CascadeType.ALL)
	private List<EducationDetails> educationDetails = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> address;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<MockDetails> mockDetails = new ArrayList<>();

	// @NotNull(message = "NULL data passed for bankDetails")
	@OneToOne(cascade = CascadeType.ALL)
	private BankDetails bankDetails;

	@NotNull(message = "NULL data passed for technicalSkill")
	@OneToMany(cascade = CascadeType.ALL)
	private Set<TechnicalSkill> technicalSkill = new HashSet<>();

	@Nullable
	@OneToMany(cascade = CascadeType.ALL)
	private List<Experience> experience = new ArrayList<>();

	@NotNull(message = "NULL data passed for contacts")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Contact> contacts = new ArrayList<>();
}
