package com.registration.dao;

import com.registration.dbconnection.BCrypt;
import com.registration.model.UserLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserLoginInsert {

    Connection connection;

    public UserLoginInsert(Connection connection) {

        this.connection = connection;
    }

    public boolean insertLoginData(UserLogin user) {

        boolean isDataInserted = false;

        try {

            //here i'm using hashpw() of BCrypt class. hashpw() will convert password into encrypted form
//            and store in encryptedPassword variable
            String encryptedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12));

            String userSql = "INSERT INTO userlogin(userLoginId, password, partyId)" +
                    "VALUES(?,?,?)";

            PreparedStatement statement = connection.prepareStatement(userSql);
            statement.setString(1, user.getLoginId());
            statement.setString(2, encryptedPassword);      //pass encryptedPassword in db
            statement.setInt(3, user.getPartyId());

            statement.executeUpdate();
            isDataInserted = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDataInserted;
    }
}
