package com.registration.controller;

import com.registration.dao.DisplayData;
import com.registration.dbconnection.ConnectionProvider;
import com.registration.model.Party;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/profile")
public class HomeProfilePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//------------------------------use for showing data on Homepage------------------------------------
        ArrayList<Party> studentList = DisplayData.getStudent();

        req.setAttribute("studentList", studentList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("HomePage.jsp");     //(HomePage.jsp)it's main profile page
        requestDispatcher.include(req, resp);

    }
}
