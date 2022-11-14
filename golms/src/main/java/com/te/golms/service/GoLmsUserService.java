package com.te.golms.service;

import java.util.Optional;

import com.te.golms.entity.Admin;
import com.te.golms.entity.Employee;
import com.te.golms.entity.Mentor;
import com.te.golms.entity.model.EmployeeModel;
import com.te.golms.entity.model.PasswordResetModel;

public interface GoLmsUserService {
	public abstract EmployeeModel saveEmployee(EmployeeModel employeeModel);
	
	public abstract Optional<Admin> findByAdminId(String adminId);

	public abstract Optional<Mentor> findByMentorId(String mentorId);

	public abstract boolean passwordReset(PasswordResetModel reset);

	public abstract Boolean firstTimeLoggedIn(String id);

	public abstract Optional<Employee> findByEmpId(String empId);

	public abstract Boolean setLastLogin(String empId);

	public abstract boolean checkPasswordReset(String empId);
}
