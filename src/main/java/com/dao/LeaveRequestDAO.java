package com.dao;

import java.util.List;

import com.model.Leave;



public interface LeaveRequestDAO {

	boolean updateStatus(int requestId, String status) throws ClassNotFoundException;

	String getEmployeeEmailByRequestId(int requestId) throws ClassNotFoundException;

	List<Leave> getLeavesByEmployee(String employeeEmail);

}
