package com.registration.controller;

import com.registration.dao.PartyDao;
import com.registration.dao.UserLoginInsert;
import com.registration.dbconnection.ConnectionProvider;
import com.registration.model.Party;
import com.registration.model.UserLogin;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

//    this method will be used for sending the mail when someone register on our site
    public static void sendMail(Party party, UserLogin userLogin) throws IOException {

        // it's messege for sending in the mail
        String sms = " Hello " + party.getFirstName() + " " + party.getLastName() +
                " Thank you for registering on our site please check your details once!" +
                "\n Address :" + party.getAddress() + "\nCity :" + party.getCity() + "\nState :" + party.getState() +
                "\nzip :" + party.getZip() + "\nCountry :" + party.getCountry() + "\nNumber :" + party.getPhone() +
                "\nEmail Id :" + userLogin.getLoginId() + "\n Password :" + userLogin.getPassword();

        String subject = "thanks for sing in our app";
        String to = userLogin.getLoginId();
        String from = "mustak.patel@hotwaxsystems.com";

        String host = "smtp.gmail.com";     //it's host for gmail (which server we are gonna use)
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);         //set the host
        properties.put("mail.smtp.port", "465");         //it's  port number for gmail
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");       //for authentication purpose
        FileInputStream fileInputStream = new FileInputStream(
                "/home/mustak/IdeaProjects/SendMail/src/main/resources/config.properties");

        properties.load(fileInputStream);
        final String username = properties.getProperty("username");
        final String password = properties.getProperty("password");

//        step 1)------------------to get the session object--------------------------

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);

//        step2-----------------------compose the message-----------------------------

        MimeMessage message = new MimeMessage(session);  //this is the class where we write our message

        try {
            message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            message.setSubject(subject);
            message.setText(sms);

//       step3---------------------------send the message------------------------------

            Transport.send(message);
            System.out.println("email send successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

//        -------------------parameters is being taken from SignUp.html page--------------

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String country = req.getParameter("country");
        String zip = req.getParameter("zip");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Party party = new Party();      // reference variable is created for (model) party

//        ----------------- value is being taken in party's setter --------------------------------------
        party.setFirstName(firstname);
        party.setLastName(lastname);
        party.setAddress(address);
        party.setCity(city);
        party.setState(state);
        party.setCountry(country);
        party.setZip(zip);
        party.setPhone(phone);


        PartyDao p = new PartyDao(ConnectionProvider.getConnection());
        int partyId = p.userID();

        UserLogin userLogin = new UserLogin();      // reference variable is created for (model) UserLogin

//        ----------------- value is being taken in UserLogin's setter --------------------------------------
        userLogin.setLoginId(email);
        userLogin.setPassword(password);
        userLogin.setPartyId(partyId);

        PartyDao partyDao = new PartyDao(ConnectionProvider.getConnection());       //PartyDao is a class where Insertion operation will be performed
        UserLoginInsert userLoginInsert = new UserLoginInsert(ConnectionProvider.getConnection());      //PartyDao is a class where Insertion operation will be performed
        if (partyDao.insertUser(party) && userLoginInsert.insertLoginData(userLogin)) {       //if both condition true

            sendMail(party, userLogin);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Registration successfull please login');");
            out.println("</script>");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");        //its login page
            requestDispatcher.include(req, resp);
        } else {
            System.out.println("failed");
        }
    }
}
