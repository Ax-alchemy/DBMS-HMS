<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/dashboard.css">
<link rel="stylesheet" href="/css/signin.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Book Slot</title>
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
            <div class="div-center">


				    <div class="content">
				
				
				      <h3>Book Appointment</h3>
				      <hr />
				      <form action="/bookslot" method="post">
				        <div class="form-group">
				          <!-- <label for="date">Email address</label> -->
				          <input type="date" class="form-control" name="date" value="${date }" hidden>
				        </div>
				        <div class="form-group">
				          <input type="email" class="form-control" name="doc_email" value="${doc_email }" hidden>
				        </div>
				        <div class="form-group">
				        	<label for="slot">Choose a slot:</label>
							<select id="slot" name="slot" required>
							  <c:forEach var="item" items="${avl_slots }">
							  	<option value=${item }>${map[item] }</option>
							  </c:forEach>
							</select>
				        </div>
				        <div class="form-group">
				          <label for="concerns">Concerns</label>
				          <textarea class="form-control" id="concerns" name="concerns" rows="3" placeholder="Your concerns" required></textarea>
				        </div>
				        <div class="form-group">
				          <label for="symptoms">Symptoms</label>
				          <textarea class="form-control" id="symptoms" name="symptoms" rows="3" placeholder="Your symptoms" required></textarea>
				        </div>
				        
				        <br>	
				        <button type="submit" class="btn btn-success">Book Appointment</button>
				        <hr />
				      </form>
				      
				      
				
				    </div>
				
				  </div>
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
	
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
</body>
</html>