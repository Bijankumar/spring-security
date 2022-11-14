package com.te.golms.service;

import com.te.golms.entity.Employee;
import com.te.golms.entity.model.AttendenceInfoModel;
import com.te.golms.entity.model.EmployeeModel;

public interface EmployeeService {

	public abstract Employee getEmpDetails(String empId);

	public abstract Boolean addAttendenceInfo(AttendenceInfoModel attendenceInfoModel);

	public abstract Boolean updateEmpDetails(String empId, EmployeeModel employeeModel);

}
