package com.te.golms.entity;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@Entity
@Table(name = "batch")
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "batch_id")
	private Integer batchId;

	@NotNull(message = "NULL data passed for batchName")
	@NotBlank(message = "BLANK data passes batchName")
	@Column(name = "batch_name", unique = true)
	private String batchName;

	@NotNull(message = "NULL data passed for batchStartDate")
	@Column(name = "batch_start_date")
	private LocalDate batchStartDate;

	@Column(name = "batch_end_date")
	private LocalDate batchEndDate;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Technology> technologies = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable
	private Map<BatchStrength, Integer> batchStrength = new EnumMap<>(BatchStrength.class);

	@NotNull(message = "NULL data passed for batchStatus")
	@Enumerated(EnumType.STRING)
	private BatchStatus batchStatus;

	@OneToOne
	private Mentor batchMentor;

	@OneToMany(mappedBy = "inBatch", cascade = CascadeType.ALL)
	private List<Employee> employees;

}
