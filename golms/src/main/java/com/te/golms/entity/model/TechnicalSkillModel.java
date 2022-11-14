package com.te.golms.entity.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.golms.enums.SkillRating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalSkillModel {
	private String skillType;
	@Enumerated(EnumType.STRING)
	private SkillRating skillRating;
	private Integer yearOfExperienceOnSkill;
}
