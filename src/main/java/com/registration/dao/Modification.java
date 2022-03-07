package com.registration.dao;

import com.registration.dbconnection.ConnectionProvider;
import com.registration.model.Party;
import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/Modification")
public class Modification {

    public static ArrayList<Party> userData(Party party) {

        ArrayList<Party> parties = new ArrayList<>();
        try {

            String sql = "SELECT p.partyId, firstName, lastName, address, city," +
                    " zip, state, country, phone,userLoginId FROM party p," +
                    " userlogin u WHERE p.partyId = u.partyId AND p.partyId ='" + party.getPartyId() + "'";

            Connection connection = ConnectionProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                party.setPartyId(resultSet.getInt(1));
                party.setFirstName(resultSet.getString(2));
                party.setLastName(resultSet.getString(3));
                party.setAddress(resultSet.getString(4));
                party.setCity(resultSet.getString(5));
                party.setZip(resultSet.getString(6));
                party.setState(resultSet.getString(7));
                party.setCountry(resultSet.getString(8));
                party.setPhone(resultSet.getString(9));
                parties.add(party);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parties;
    }

}