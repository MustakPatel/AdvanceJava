package com.registration.dao;

import com.registration.dbconnection.BCrypt;
import com.registration.model.UserLogin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// this class will be used for checking  is user exist or not in the sense of login

public class CheckLogin {

    Connection connection;

    public CheckLogin(Connection connection) throws IOException {       //connection will be initialized by ConnectionProvider class
        this.connection = connection;
    }

    public boolean isValidateData(UserLogin userLogin) {     // it will return boolean type value

        boolean selectedData = false;
        try {
            BCrypt bCrypt = new BCrypt();       //it's class which will work for data encryption and decryption

            String ensql = "SELECT password FROM userlogin " +
                    "WHERE userLoginId='" + userLogin.getLoginId() + "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ensql);

            if (resultSet.next()) {

//               here checkpw() method is being checked user input password with db password
//                if password is same then it will return true otherwise false
                if (BCrypt.checkpw(userLogin.getPassword(), resultSet.getString(1))) {

                    selectedData = true;

                } else {

                    System.out.println("didn't metch password");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return selectedData;
    }
}

