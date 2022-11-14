package com.te.golms.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@Entity
@Table(name = "mock_details")
public class MockDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mock_id")
	private Integer mockId;

	@NotNull(message = "NULL data passed for mockDate")
	@Column(name = "mock_date")
	private LocalDate mockDate;

	@NotNull(message = "NULL data passed for practical")
	@Column(name = "practical")
	private Integer practical;

	@NotNull(message = "NULL data passed for theoritical")
	@Column(name = "theoritical")
	private Integer theoritical;

	@NotNull(message = "NULL data passed for mockFeedback")
	@NotBlank(message = "BLANK data passes mockFeedback")
	@Column(name = "mock_feedback")
	private String mockFeedback;

	@Enumerated(EnumType.STRING)
	private MockType mockType;

	@Enumerated(EnumType.STRING)
	private MockOn mockOn;

	@Enumerated(EnumType.STRING)
	private MockRating mockRating;

	@ManyToOne
	private Employee employee;

	@ManyToMany
	private List<Mentor> mentor;
}
