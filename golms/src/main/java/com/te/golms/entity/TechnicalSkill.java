package com.te.golms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.te.golms.enums.SkillRating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "technical_skills_info")
public class TechnicalSkill {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "skill_id")
	private Integer skillId;

	@NotNull(message = "NULL data passed for skillType")
	@NotBlank(message = "BLANK data passes skillType")
	@Column(name = "skill_type")
	private String skillType;

	@NotNull(message = "NULL data passed for skillRating")
	@Enumerated(EnumType.STRING)
	private SkillRating skillRating;

	@NotNull(message = "NULL data passed for yearOfExperienceOnSkill")
	@Column(name = "year_of_experience_on_skill")
	private Integer yearOfExperienceOnSkill;
}
