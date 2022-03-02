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

<body>
<%@ include file="Navbar.jsp" %>
    <%
           String name = request.getParameter("search");
           Connection connection = DriverManager.getConnection(
             "jdbc:mysql://localhost:3306/javaproject", "root", "123456");

           String sql = "SELECT * FROM userlogin u INNER JOIN party p ON u.partyId = p.partyId "+
                        " WHERE concat(firstName,' ',lastname) LIKE ?";

           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1,"%"+name+"%");
           ResultSet resultset = statement.executeQuery();
    %>
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
                                <td> <%= resultset.getInt("partyId") %></td>
                                <td> <%= resultset.getString("firstName") %></td>
                                <td> <%= resultset.getString("lastName") %></td>
                                <td> <%= resultset.getString("address") %></td>
                                <td> <%= resultset.getString("city") %></td>
                                <td>  <%= resultset.getString("state") %></td>
                                <td> <%= resultset.getString("country") %></td>
                                <td> <%= resultset.getString("zip") %></td>
                                <td> <%= resultset.getString("phone") %></td>
                                <td> <%= resultset.getString("userLoginid") %></td>
                            </tr>
                        <% } %>
                 </tbody>
           </table>
</body>
</html>