<%@ page errorPage="Error.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.registration.model.*" %>

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
<% ArrayList<Party> std = (ArrayList<Party>) request.getAttribute("studentList");%>
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
                        <th>Action</th>


                    </tr>
                </thead>
                <tbody>
                 <% for(Party s:std){%>
                    <tr>
                        <td> <%=s.getPartyId()%></td>
                        <td> <%=s.getFirstName()%></td>
                        <td> <%=s.getLastName()%></td>
                        <td> <%=s.getAddress()%></td>
                        <td> <%=s.getCity()%></td>
                        <td> <%=s.getZip()%></td>
                        <td> <%=s.getState()%></td>
                        <td> <%=s.getCountry()%></td>
                        <td> <%=s.getPhone()%></td>

                        <td>
                            <a class="add" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>
                            <a href="ModifyServlet?id=<%=s.getPartyId()%>" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                            <a href="DeleteServlet?id=<%=s.getPartyId()%>" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
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