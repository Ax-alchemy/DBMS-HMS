<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/dashboard.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Your Appointments</title>
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
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <h1>${doctor.getName() }</h1>
                <h3>Your Appointments</h3>
                <table class="table table-striped">
                	<thead>
                		<th scope="col">Date</th>
                		<th scope="col">Time</th>
                		<th scope="col">Patient</th>
                		<th scope="col">Concerns</th>
                		<th scope="col">Symptoms</th>
                		<th scope="col">Diagnosis</th>
                		<th scope="col">Prescription</th>
                		<th scope="col">Status</th>
                	</thead>
                	<tbody>
                		<c:forEach var="item" items="${list }">
                			<tr>
	                			<td>${item.date }</td>
	                			<td>${item.startTime }</td>
	                			<td>${item.patient.name }</td>
	                			<td>${item.appointment.concerns }</td>
	                			<td>${item.appointment.symptoms }</td>
	                			<c:choose>
		                			<c:when test="${item.appointment.status eq 'pending'}">
			                			<td>
			                				<form action="attendappointment" method="post">
			                					<textarea name="diagnosis" rows="2" cols="12" required></textarea>
			                			</td>
			                			<td>
			                					<textarea name="prescription" rows="2" cols="12" required></textarea>
			                			</td>
			                			<td>${item.appointment.status }</td>
			                			<td>
			                					<input type="number" name="sch_id" value="${item.id }" hidden>
			                					<input type="email" name="pat_email" value="${item.patient.email }" hidden>
			                					<button type="submit">Done</button>
			                				</form>
			                				<form action="/canceldoctorappointment" method="post">
												<input type="number" name="sch_id" value="${item.id }" hidden>
												<button class="btn-info" type="submit" onclick="return confirm('Are you sure to cancel the appointment?');">Cancel</button>
											</form>
			                			</td>
			                		</c:when>
			                		<c:otherwise>
			                			<td>${item.appointment.getDiagnosis() }</td>
			                			<td>${item.appointment.prescription }</td>
			                			<td>${item.appointment.status }</td>
			                		</c:otherwise>
	                			</c:choose>
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