package com.te.golms.entity;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.golms.enums.MockOn;
import com.te.golms.enums.MockRating;
import com.te.golms.enums.MockType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MockDetailsModel {
	private LocalDate mockDate;
	private Integer practical;
	private Integer theoritical;
	private String mockFeedback;

	@Enumerated(EnumType.STRING)
	private MockType mockType;

	@Enumerated(EnumType.STRING)
	private MockOn mockOn;

	@Enumerated(EnumType.STRING)
	private MockRating mockRating;
}
