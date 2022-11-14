package com.te.spring.boot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.spring.boot.beans.Student;

@RestController
@RequestMapping(path = "management/api/v1/student")
public class StudentManagementController {

	private List<Student> studentList = Arrays.asList(new Student(1L, "Student01"), new Student(2L, "Student02"),
			new Student(3L, "Student03"), new Student(4L, "Student04"));

	@GetMapping(path = "/{studentId}")
	public Student getStudent(@PathVariable("studentId") Long studentId) {
		return studentList.stream().filter(student -> studentId.equals(student.getStudentId())).findFirst()
				.orElseThrow(() -> new IllegalStateException("Student does not exist!"));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	public List<Student> getAllStudent() {
		return studentList;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('student: write')")
	public Student registerNewStudent(@RequestBody Student student) {
		System.out.println(student);
		return student;
	}

	@DeleteMapping(path = "{studentId}")
	@PreAuthorize("hasAuthority('student: write')")
	public Integer deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println(studentId);
		return studentId;
	}

	@PutMapping(path = "{studentId}")
	@PreAuthorize("hasAuthority('student: write')")
	public String updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
		System.out.println(studentId + ": " + student);
		return studentId + ": " + student;
	}
}
