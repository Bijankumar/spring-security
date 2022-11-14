package com.te.golms.service;

import java.util.List;

import com.te.golms.entity.Request;
import com.te.golms.entity.model.AddEmployeeToBatchModel;
import com.te.golms.entity.model.BatchModel;
import com.te.golms.entity.model.MentorModel;
import com.te.golms.entity.model.RejectRegisterRequestModel;
import com.te.golms.response.BatchResponseModel;

public interface AdminService {
	public abstract List<BatchResponseModel> getBatcheList();

	public abstract BatchModel saveBatch(BatchModel batchModel);

	public abstract BatchModel updateBatch(BatchModel batchModel);

	public abstract Boolean deleteBatch(String batchName);

	public abstract Boolean addEmployeeToBatchModel(AddEmployeeToBatchModel addEmployeeToBatchModel);

	public abstract List<Request> getRequestList();

	public abstract Boolean rejectRequest(RejectRegisterRequestModel rejectRequest);

	public abstract MentorModel saveMentor(MentorModel mentorModel);

	public abstract List<MentorModel> getMentorList();

	public abstract Boolean deleteMentor(String mentorName);

}
