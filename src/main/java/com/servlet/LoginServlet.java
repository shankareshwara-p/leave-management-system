package com.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.UserInfo;
import com.service.LeaveManageService;
import com.service.UserInfoService;



public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserInfoService userInfoService = new UserInfoService(); // initialize manually
    private LeaveManageService leaveManageService = new LeaveManageService(); // initialize manually

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("userInfo") != null) {
            // user already logged in -> redirect to home
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            // not logged in -> show login page
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserInfoService service = new UserInfoService();
        UserInfo user = service.findUserByEmail(email);

        response.setContentType("text/plain"); // very important
        System.out.println(user.getPassword().equals(password));
        if(user != null && user.getPassword().equals(password)){
            request.getSession().setAttribute("user", user);
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }
    }
}
