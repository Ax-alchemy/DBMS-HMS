<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/signin.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Login as Patient</title>
<link rel="icon" href="/images/hospital_logo2.png" type="image/x-icon">
</head>
<body>
	<nav class="navbar navbar-light" style="background-color: aqua;">
	  <a class="navbar-brand" href="/" style="padding-left: 2em; size: 2em;">HMS</a>
	  <h5 style="text-align: center; margin-left: auto; margin-right: auto;">Welcome to Medicity Hospital</h5>
	  <a class="navbar-link" href="/patsignin" style="text-decoration: none; padding-right: 1em;"><i class="fa fa-2x fa-sign-in"></i></a>
	  <a class="navbar-link" href="/patregistration" style="text-decoration: none; padding-right: 2em;"><i class="fa fa-2x fa-user-plus"></i></a>
	</nav>
	<div class="div-center">


    <div class="content">
	
	  <h4 style="color: red;">${message }</h4>
      <h3>Patient Login</h3>
      <hr />
      <form action="/loginuser" method="post">
        <div class="form-group">
          <label for="exampleInputEmail1">Email address</label>
          <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email" name="email" required>
        </div>
        <div class="form-group">
          <label for="exampleInputPassword1">Password</label>
          <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password" required>
        </div><br>
        <button type="submit" class="btn btn-primary">Login</button>
        <hr />
      </form>
      <h5>Not registered yet?</h5>
      <a href="/patregistration"><button class="btn btn-primary">Register</button></a><br><br>

	  <a href="/docsignin"><button class="btn btn-warning">Login as Doctor</button></a>
    </div>


    
  </div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>