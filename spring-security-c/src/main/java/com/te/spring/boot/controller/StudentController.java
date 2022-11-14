package com.te.spring.boot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.spring.boot.beans.Student;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

	private List<Student> studentList = Arrays.asList(new Student(1L, "Student01"), new Student(2L, "Student02"),
			new Student(3L, "Student03"), new Student(4L, "Student04"));

	@GetMapping(path = "/{studentId}")
	public Student getStudent(@PathVariable("studentId") Long studentId) {
		return studentList.stream().filter(student -> studentId.equals(student.getStudentId())).findFirst()
				.orElseThrow(() -> new IllegalStateException("Student does not exist!"));
	}
}
