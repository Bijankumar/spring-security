package com.te.golms.service;

import com.te.golms.entity.MockDetailsModel;
import com.te.golms.entity.model.BatchModel;
import com.te.golms.entity.model.EmpStatusModel;
import com.te.golms.entity.model.MockModel;

public interface MentorService {
	public abstract BatchModel getBatchData(String username);

	public abstract Boolean addEmpMockData(String empId, MockDetailsModel mockDetailsModel);

	public abstract Boolean changeEmployeeStatus(EmpStatusModel empStatusModel);

	public abstract Boolean createNewMock(String mentorId, MockModel mockModel);
}
