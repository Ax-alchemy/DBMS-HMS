<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/dashboard.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Available Doctors</title>
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
                <h4 style="color: red;">${msg }</h4>
				<h2>Select doctor and appointment date</h2>
				<table class="table table-striped">
					<thead>
						<th scope="col">Name</th>
						<th scope="col">Gender</th>
						<th scope="col">Speciality</th>
						<th scope="col">Date</th>
						<th scope="col">Slots</th>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}">
							<tr>
								<td>${item.name }</td>
								<td>${item.gender }</td>
								<td>${item.speciality }</td>
								<td>
									<form action="/viewslots" method="post">
										<input type="date" name="app_date" required>
								</td>
								<td>
									
										<input type="email" name="doc_email" value="${item.email }" hidden>
										<button type="submit">View slots</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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