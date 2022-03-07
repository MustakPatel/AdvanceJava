package com.registration.controller;

import com.registration.dao.SearchData;
import com.registration.model.Party;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("search");

        Party party = new Party();
        party.setFirstName(name);

        ArrayList<Party> searchdata = SearchData.searching(party);
        req.setAttribute("searchData", searchdata);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Search.jsp");
        requestDispatcher.forward(req, resp);

    }
}
