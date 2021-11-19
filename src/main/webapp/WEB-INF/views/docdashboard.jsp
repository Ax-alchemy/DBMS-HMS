<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/dashboard.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Your Dashboard</title>
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
                <h2 style="text-align: center;">Welcome ${doctor.getName() }</h2>
                <h3 style="text-align: center;">${doctor.speciality }</h3>
                <img style="width: 100%;" alt="No preview available." src="/images/doctor.jpg">
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