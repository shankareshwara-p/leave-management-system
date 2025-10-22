package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.ManagerService; // import your service
import com.service.ManagerServiceImpl;

public class ManagerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ManagerService managerService;

    @Override
    public void init() {
        // ✅ Now getServletContext() is safe
        managerService = new ManagerServiceImpl(getServletContext());
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters
        String action = request.getParameter("action");   // e.g., "accept" or "reject"
        String leaveIdStr = request.getParameter("leaveId");

        response.setContentType("text/plain");
        
        // Validate input
        if (action == null || leaveIdStr == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing required parameters");
            return;
        }

        int leaveId = Integer.parseInt(leaveIdStr);

        // Switch case for different actions
        switch (action.toLowerCase()) {
            case "accept":
			try {
				managerService.updateLeaveStatus(leaveId, "Accepted");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                response.getWriter().write("Leave accepted successfully");
                break;

            case "reject":
			try {
				managerService.updateLeaveStatus(leaveId, "Rejected");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                response.getWriter().write("Leave rejected successfully");
                break;

            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid action");
                break;
        }
    }
}
