package com.registration.controller;

import com.registration.dao.Modification;
import com.registration.model.Party;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        Party party = new Party();
        party.setPartyId(Integer.parseInt(id));

        ArrayList<Party> data = Modification.userData(party);

        req.setAttribute("userData", data);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Update.jsp");
        requestDispatcher.forward(req, resp);

    }
}
