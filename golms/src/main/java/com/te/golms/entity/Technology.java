package com.te.golms.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Technology {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "technology_id")
	private Integer technologyId;

	@Column(name = "technology_name")
	private String technologyName;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Mentor> mentorSet;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Batch> batchSet;
}
