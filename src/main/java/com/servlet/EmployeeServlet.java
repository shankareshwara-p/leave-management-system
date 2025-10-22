package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.LeaveRequestDAO;
import com.dao.LeaveRequestDAOImpl;
import com.google.gson.Gson;
import com.model.Leave;


public class EmployeeServlet extends HttpServlet {

    private LeaveRequestDAO leaveDAO;

    @Override
    public void init() throws ServletException {
        leaveDAO = new LeaveRequestDAOImpl(); // initialize your DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Get filter parameters from checkbox
        boolean pending = Boolean.parseBoolean(request.getParameter("pending"));
        boolean accepted = Boolean.parseBoolean(request.getParameter("accepted"));
        boolean rejected = Boolean.parseBoolean(request.getParameter("rejected"));

        // Get logged-in employee id/email from session (adjust according to your auth)
        HttpSession session = request.getSession(false);
//        String employeeEmail = (session != null) ? (String) session.getAttribute("email") : null;
//
//        if (employeeEmail == null) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("{\"error\":\"Not logged in\"}");
//            return;
//        }

        // Fetch leaves from DAO
        List<Leave> leaves = leaveDAO.getLeavesByEmployee("shankar");

        List<Map<String, String>> filteredEvents = new ArrayList<>();
        boolean allFalse = !pending && !accepted && !rejected; // check if all filters are false

        for (Leave leave : leaves) {
            String status = leave.getStatus().toLowerCase();

            // Include leave if:
            // 1. all filters are false (first load) OR
            // 2. the leave status matches a checked filter
            if (allFalse || 
                (status.equals("pending") && pending) ||
                (status.equals("accepted") && accepted) ||
                (status.equals("rejected") && rejected)) {

                Map<String, String> event = new HashMap<>();
                event.put("id", String.valueOf(leave.getId()));
                event.put("title", "Leave - " + status);
                event.put("start", leave.getStartDate().toString());
                event.put("end", leave.getEndDate().toString());
                event.put("status", status);
                filteredEvents.add(event);
            }
        }

        // Send JSON response
        String json = new Gson().toJson(filteredEvents);
        response.getWriter().write(json);
    }
}
