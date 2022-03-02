<%@ page errorPage="Error.jsp" %>
<%@ page import="java.sql.*" %>
<%@ page import="main.java.com.registration.*" %>
<%@ page import="main.java.com.registration.dbconnection.*" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>HomePage</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Main css -->
    <link rel="stylesheet" href="css/HomePage.css">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
 <%
    String uname = (String) session.getAttribute("userName");

    if (uname == null) {
        session.setAttribute("errorMessage", "please login first you are new user");
        response.sendRedirect("index.jsp");
    }
 %>
<body>

  <%
           Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/javaproject", "root", "123456");

           Statement statement = connection.createStatement() ;
           ResultSet resultset = statement.executeQuery("SELECT p.partyId, firstName, lastName, address, city," +
                                                         " zip, state, country, phone,userLoginId FROM party p," +
                                                         " userlogin u WHERE p.partyId = u.partyId") ;
  %>

<%@ include file="Navbar.jsp" %>

<div class="container-lg">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2>Users <b>Details</b></h2></div>
                </div>
            </div>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>zip</th>
                        <th>State</th>
                        <th>Country</th>
                        <th>Phone</th>
                        <th>Email Id</th>

                    </tr>
                </thead>
                <tbody>
                 <% while(resultset.next()){ %>
                    <tr>
                        <td> <%= resultset.getInt(1) %></td>
                        <td> <%= resultset.getString(2) %></td>
                        <td> <%= resultset.getString(3) %></td>
                        <td> <%= resultset.getString(4) %></td>
                        <td> <%= resultset.getString(5) %></td>
                        <td>  <%= resultset.getString(6) %></td>
                        <td> <%= resultset.getString(7) %></td>
                        <td> <%= resultset.getString(8) %></td>
                        <td> <%= resultset.getString(9) %></td>
                        <td> <%= resultset.getString(10) %></td>
                        <td>
                            <a class="add" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>
                            <a href="Update.jsp?id=<%=resultset.getInt("partyId")%>" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                            <a href="DeleteServlet?id=<%=resultset.getInt("partyId")%>" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                        </td>
                    </tr>
                     <% } %>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>