package com.te.golms.entity.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorModel {
	private String mentorId;
	private String mentorName;
	private String email;
	private Set<TechnologyModel> technologies;
}
