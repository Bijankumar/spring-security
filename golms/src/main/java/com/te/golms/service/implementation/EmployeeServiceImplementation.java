package com.te.golms.service.implementation;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.golms.entity.BankDetails;
import com.te.golms.entity.Employee;
import com.te.golms.entity.EmployeeAttendence;
import com.te.golms.entity.SecondaryInfo;
import com.te.golms.entity.model.AttendenceInfoModel;
import com.te.golms.entity.model.EmployeeModel;
import com.te.golms.repository.EmployeeRepository;
import com.te.golms.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	public Employee getEmpDetails(String empId) {
		Optional<Employee> op = employeeRepository.findByEmpId(empId);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public Boolean addAttendenceInfo(AttendenceInfoModel attendenceInfoModel) {
		EmployeeAttendence employeeAttendence = new EmployeeAttendence();
		BeanUtils.copyProperties(attendenceInfoModel, employeeAttendence);
		Optional<Employee> op = employeeRepository.findByEmpId(attendenceInfoModel.getEmpId());
		if (op.isPresent()) {
			Employee employee = op.get();
			boolean isThere = employee.getEmployeeAttendence().contains(employeeAttendence);
			if (isThere) {
				return false;
			}
			employee.getEmployeeAttendence().add(employeeAttendence);
			employeeRepository.save(employee);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateEmpDetails(String empId, EmployeeModel employeeModel) {
		Employee employee = new Employee();
		SecondaryInfo secondaryInfo = new SecondaryInfo();
		BankDetails bankDetails = new BankDetails();
		Optional<Employee> op = employeeRepository.findByEmpId(empId);
		if (op.isPresent()) {
			BeanUtils.copyProperties(employeeModel, employee);
			BeanUtils.copyProperties(employeeModel.getSecondaryInfo(), secondaryInfo);
			BeanUtils.copyProperties(employeeModel.getBankDetails(), bankDetails);
			employee.setSecondaryInfo(secondaryInfo);
			employee.setBankDetails(bankDetails);
			employeeRepository.save(employee);
			return true;
		}
		return false;
	}

}
