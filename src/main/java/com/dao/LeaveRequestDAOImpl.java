package com.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.model.Leave;

public class LeaveRequestDAOImpl implements LeaveRequestDAO {

    @Override
    public boolean updateStatus(int requestId, String status) throws ClassNotFoundException {
//        String sql = "UPDATE leave_request SET status = ? WHERE request_id = ?";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, status);
//            ps.setString(2, requestId);
//
//            int rows = ps.executeUpdate();
//            return rows > 0; // true if at least one row updated
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
    	return true;
    }

    @Override
    public String getEmployeeEmailByRequestId(int requestId) throws ClassNotFoundException {
//        String sql = "SELECT email FROM leave_request lr join users u on u.id = lr.emp_id  WHERE request_id = ?";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, requestId);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getString("email");
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null; // if not found
    	return "smartshankar1311@gmail.com";
    }

	@Override
	public List<Leave> getLeavesByEmployee(String employeeEmail) {
		
		

		

		Leave leave1 = new Leave(1, "pending", Date.valueOf("2025-10-22"), Date.valueOf("2025-10-24"));
		Leave leave2 = new Leave(2, "accepted", Date.valueOf("2025-10-25"), Date.valueOf("2025-10-26"));
		Leave leave3 = new Leave(3, "rejected", Date.valueOf("2025-10-28"), Date.valueOf("2025-11-05"));

		List<Leave> li= new ArrayList<>();
		li.add(leave1);
		li.add(leave2);
		li.add(leave3);

		return li;
	}
}
