package com.registration.dao;

import com.registration.dbconnection.ConnectionProvider;
import com.registration.model.Party;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/SearchData")
public class SearchData {


    public static ArrayList<Party> searching(Party party) {

        ArrayList<Party> parties = new ArrayList<>();


        try {

            String sql = "SELECT * FROM party WHERE concat(firstName,' ',lastname) LIKE ?";

            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + party.getFirstName() + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Party partyEntity = new Party();
                partyEntity.setPartyId(resultSet.getInt(1));
                partyEntity.setFirstName(resultSet.getString(2));
                partyEntity.setLastName(resultSet.getString(3));
                partyEntity.setAddress(resultSet.getString(4));
                partyEntity.setCity(resultSet.getString(5));
                partyEntity.setState(resultSet.getString(6));
                partyEntity.setCountry(resultSet.getString(7));
                partyEntity.setZip(resultSet.getString(8));
                partyEntity.setPhone(resultSet.getString(9));
                parties.add(partyEntity);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parties;
    }
}
