package com.te.golms.entity.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.te.golms.entity.Technology;
import com.te.golms.enums.MockOn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MockModel {
	@Enumerated(EnumType.STRING)
	private MockOn mockOn;

	@OneToOne(cascade = CascadeType.ALL)
	private Technology technology;

	@OneToOne
	private MentorModel mentor;

	@Column(name = "mock_time")
	private LocalDateTime mockTime;
}
