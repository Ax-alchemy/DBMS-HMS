<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/dashboard.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Settings</title>
<link rel="icon" href="/images/hospital_logo2.png" type="image/x-icon">
</head>
<body>
	<div id="wrapper" class="toggled">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="/" id="hms">
                        HMS
                    </a>
                </li>
                <li>
                    <a href="/patdashboard">Dashboard</a>
                </li>
                <li>
                    <a href="/pathistory">Medical History</a>
                </li>
                <li>
                    <a href="/patientappointments">Appointments</a>
                </li>
                <li>
                    <a href="/viewdoctors">Book appointment</a>
                </li>
                <li>
                    <a href="/patientsettings">Settings</a>
                </li>
                <li>
                    <a href="/patlogout">Logout</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
            	<h1>${patient.name }</h1><hr>
                <b>Email: </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${patient.email }
                
                <br><br>
                <b>Name: </b><input style="width: 50%; margin-left: 3em;" type="text" value="${patient.name }" readonly><br><br>
                <b>Address: </b><input style="width: 50%; margin-left: 2em;" type="text" value="${patient.address }" readonly>
                <button class="btn-info" onclick="document.getElementById('updateaddress').style.display='';"><i class="fa fa-edit"></i></button>
                <form id="updateaddress" action="updateaddress" method="post" style="display: none;">
                	<input type="text" name="pat_email" value="${patient.email }" hidden>
                	<br> New Address: <input style="width: 50%;" type="text" name="address">
                	<button class="btn-success" type="submit">Update</button>
                </form>
                <br><br>
                <b>Password: </b><input style="width: 50%; margin-left: 1.2em;" type="password" value="${patient.password }" readonly>
                <button class="btn-info" onclick="document.getElementById('updatepassword').style.display='';"><i class="fa fa-edit"></i></button>
                <form id="updatepassword" action="updatepassword" method="post" style="display: none;">
                	<input type="text" name="pat_email" value="${patient.email }" hidden>
                	<br> New Password: <input style="width: 50%;" type="password" name="password">
                	<button class="btn-success" type="submit">Update</button>
                </form>
                
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>
</body>
</html>