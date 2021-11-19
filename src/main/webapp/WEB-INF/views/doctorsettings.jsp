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
                <h1>${doctor.getName() }</h1><hr>
                <b>Email: </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${doctor.email }
                
                <br><br>
                <b>Name: </b><input style="width: 50%; margin-left: 3em;" type="text" value="${doctor.name }" readonly><br><br>
                <b>Speciality: </b><input style="width: 50%; margin-left: 1.2em;" type="text" value="${doctor.speciality }" readonly>
                <button class="btn-info" onclick="document.getElementById('updatespeciality').style.display='';"><i class="fa fa-edit"></i></button>
                <form id="updatespeciality" action="updatespeciality" method="post" style="display: none;">
                	<input type="text" name="doc_email" value="${doctor.email }" hidden>
                	<br> New Speciality: <input style="width: 50%;" type="text" name="speciality">
                	<button class="btn-success" type="submit">Update</button>
                </form>
                <br><br>
                <b>Password: </b><input style="width: 50%; margin-left: 1.2em;" type="password" value="${doctor.password }" readonly>
                <button class="btn-info" onclick="document.getElementById('updatedocpassword').style.display='';"><i class="fa fa-edit"></i></button>
                <form id="updatedocpassword" action="updatedocpassword" method="post" style="display: none;">
                	<input type="text" name="doc_email" value="${doctor.email }" hidden>
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