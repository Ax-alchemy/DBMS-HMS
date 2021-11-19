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
<title>Patient History</title>
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
                    <a href="/docdashboard">Dashboard</a>
                </li>
                <li>
                    <a href="/doctorappointments">Appointments</a>
                </li>
                <li>
                    <a href="/viewpatients">View Patients History</a>
                </li>
                <li>
                    <a href="/doctorsettings">Settings</a>
                </li>
                <li>
                    <a href="/logout">Logout</a>
                </li>
                <!-- <li>
                    <a href="#">Logout</a>
                </li> -->
                <!-- <li>
                    <a href="#">Contact</a>
                </li> -->
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <h2>${doctor.getName() }</h2><hr>
                <form action="/viewpatientbyemail" method="post">
	                <input type="search" placeholder="Search by email..." name="pat_email">
	                <button class="btn-warning" type="submit"><i class="fa fa-search"></i></button>
                </form>
                <hr>	
                
                <c:if test="${empty list }">
                	<h5 style="color: red;">No Medical History Recorded.</h5>
                </c:if>
                
                <c:forEach var="item" items="${list }">
                	<h5 style="color: green;">
                		${item.date }, Patient Name: ${item.patient.name }
                		
                	</h5>
                	<button class="btn-success" style="float: right;" onclick="document.getElementById('updatehistory${item.id}').style.display='';">Update History</button>
                	
                	<b>Conditions:</b> ${item.conditions }<br>
                	<b>Medication:</b> ${item.medication }<br>
                	<b>Surgeries:</b> ${item.surgeries }
                	<form id="updatehistory${item.id }" action="/updatehistory" style="display: none;" method="post">
                		<br>
                		<input type="number" value="${item.id }" name="id" hidden>
                		<div class="form-group">
                			<label for="conditions">Conditions:</label>
                			<input type="text" class="form-control" id="conditions" name="conditions" value="${item.conditions }">
                		</div>
                		<div class="form-group">
                			<label for="medication">Medication:</label>
                			<input type="text" class="form-control" id="medication" name="medication" value="${item.medication }">
                		</div>
                		<div class="form-group">
                			<label for="surgeries">Surgeries:</label>
                			<input type="text" class="form-control" id="surgeries" name="surgeries" value="${item.surgeries }">
                		</div>
                		<button class="btn-primary" type="submit">Submit</button>
                	</form>
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