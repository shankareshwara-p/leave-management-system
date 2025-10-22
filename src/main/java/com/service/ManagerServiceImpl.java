package com.service;

import javax.servlet.ServletContext;
import java.util.concurrent.ExecutorService;

import com.dao.LeaveRequestDAO;
import com.dao.LeaveRequestDAOImpl;

public class ManagerServiceImpl implements ManagerService {

    private LeaveRequestDAO leaveRequestDAO;
    private EmailService emailService;
    private ExecutorService executorService;

    // Constructor receives ServletContext
    public ManagerServiceImpl(ServletContext context) {
        leaveRequestDAO = new LeaveRequestDAOImpl();
        emailService = new EmailServiceImpl();
        executorService = (ExecutorService) context.getAttribute("executorService");
    }

    public String updateLeaveStatus(int requestId, String status) throws ClassNotFoundException {
        boolean isUpdated = leaveRequestDAO.updateStatus(requestId, status);

        if (isUpdated) {
            String email = leaveRequestDAO.getEmployeeEmailByRequestId(requestId);
            System.out.println("Leave status updated for requestId: " + requestId);

            executorService.submit(() -> {
                try {
                    String subject = "Leave Request " + status;
                    String body = "Your leave request (ID: " + requestId + ") has been " + status.toLowerCase() + ".";
                    emailService.sendEmail(email, subject, body);
                } catch (Exception e) {
                    e.printStackTrace(); // log errors inside the async task
                }
            });

            return "Leave request " + status.toLowerCase() + " successfully!";
        } else {
            return "Failed to update leave request!";
        }
    }
}
