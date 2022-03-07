package com.registration.dao;

import com.registration.dbconnection.ConnectionProvider;
import com.registration.model.Party;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/DisplayData")
public class DisplayData {

    public static ArrayList<Party> getStudent() {

        ArrayList<Party> fetch = new ArrayList<>();

        try {

            String sql = " SELECT p.partyId, firstName, lastName, address, city," +
                    " zip, state, country, phone,userLoginId FROM party p," +
                    " userlogin u WHERE p.partyId = u.partyId";

            Connection connection = ConnectionProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                Party partyEntity = new Party();
                partyEntity.setPartyId(resultSet.getInt(1));
                partyEntity.setFirstName(resultSet.getString(2));
                partyEntity.setLastName(resultSet.getString(3));
                partyEntity.setAddress(resultSet.getString(4));
                partyEntity.setCity(resultSet.getString(5));
                partyEntity.setZip(resultSet.getString(6));
                partyEntity.setState(resultSet.getString(7));
                partyEntity.setCountry(resultSet.getString(8));
                partyEntity.setPhone(resultSet.getString(9));
                fetch.add(partyEntity);

            }

        } catch (SQLException e) {

            System.out.println(e);

        }

        return fetch;
    }

}

