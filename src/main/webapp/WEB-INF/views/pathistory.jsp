<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/dashboard.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Your History</title>
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
                <c:forEach var="item" items="${list }">
                	<h5>${item.date }</h5>
                	<b>Conditions:</b> ${item.conditions }<br>
                	<b>Medication:</b> ${item.medication }<br>
                	<b>Surgeries:</b> ${item.surgeries }
                	<hr>
                </c:forEach>
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