<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/dashboard.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Add Patient History</title>
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
            
            	<h3>Add Medical History of Patient</h3>
            	<form action="/addhistory" method="post">
            		<div class="form-group">
			          <label for="date">Date:</label>
			          <input type="date" class="form-control" id="date" name="date" required>
			        </div>
			        <div class="form-group">
			          <label for="conditions">Conditions:</label>
			          <input type="text" class="form-control" id="conditions" placeholder="medical conditions" name="conditions" required>
			        </div>
			        <div class="form-group">
			          <label for="medication">Medication:</label>
			          <input type="text" class="form-control" id="medication" placeholder="" name="medication" required>
			        </div>
			        <div class="form-group">
			          <label for="surgeries">Surgeries:</label>
			          <input type="text" class="form-control" id="surgeries" placeholder="" name="surgeries" required>
			        </div><br>
			        <input type="email" name="pat_email" value="${pat_email }" hidden>
			        <button type="submit" class="btn btn-warning">Add History</button>
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