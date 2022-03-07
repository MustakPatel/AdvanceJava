<%@page import="java.util.ArrayList"%>
<%@ page import="com.registration.model.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Update data</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Main css -->
<link rel="stylesheet" href="css/style.css">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<!-- Font Icon -->
<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
</head>
<body>
<% ArrayList<Party> std = (ArrayList<Party>) request.getAttribute("userData");%>
<%@ include file="Navbar.jsp" %>

 <div class="main">
     <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Update Profile</h2>
                            <form method="get" action="UpdateServlet" class="register-form" id="register-form">
                                <% for(Party s:std){%>
                                 <div class="form-group">
                                    <label><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="hidden" name="partyId" value="<%=s.getPartyId()%>">
                                 </div>

                                <div class="form-group">
                                    <label><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="firstName" value="<%=s.getFirstName()%>">
                                </div>

                                <div class="form-group">
                                    <label><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="lastName" value="<%=s.getLastName()%>">
                                </div>

                                <div class="form-group">
                                    <label><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="address" value="<%=s.getAddress()%>">
                                </div>

                                <div class="form-group">
                                    <label><i class="zmdi zmdi-city"></i></label>
                                    <input type="text" name="city" value="<%=s.getCity()%>">
                                </div>

                                <div class="form-group">
                                    <label><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="zip" value="<%=s.getZip()%>">
                                </div>

                                <div class="form-group">
                                    <label><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="state" value="<%=s.getState()%>">
                                </div>

                                <div class="form-group">
                                    <label><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="country" value="<%=s.getCountry()%>">
                                </div>

                                <div class="form-group">
                                    <label><i class="zmdi zmdi-phone"></i></label>
                                    <input type="text" name="phone" value="<%=s.getPhone()%>">
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="update" id="signup" class="form-submit" value="Update"/>
                                </div>
                               <% } %>
                            </form>
                    </div>
                </div>
            </div>
        </section>
 </div>

</body>
</html>