<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign up</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="css/global.css" type="text/css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>

.bg{
	background: url("resources\images\ShoppingCartImages\background.jpg") no-repeat; width: 100%; height: 100vh;
}

form.container{
	border:1px solid #fff; 
	padding:50px 60px; 
	margin-top:20vh;
	-webkit-box-shadow: -1px 4px 26px 11px rgba(0,0,0,0.75);
	-moz-box-shadow:  -1px 4px 26px 11px rgba(0,0,0,0.75);
	box-shadow:  -1px 4px 26px 11px rgba(0,0,0,0.75);
}

#cancel{
	width: 10%;
	float: center;
}

a{
	float:right
}

body {
	font-family: Arial, Helvetica, sans-serif;
}

form {
	border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: white;
	color: black;
	padding: 14px 20px;
	margin: 8px 0;
	border: 2px solid #008CBA;
	cursor: pointer;
	width: 100%;
}

button:hover {
	background-color: #008CBA;
    color: white;
}

.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: lightblue;
}

.imgcontainer {
	padding-top: 10px;
	text-align: center;
	margin: 6px 0 3px 0;
	padding-bottom: 15px;
}

img.avatar {
	width: auto;
	border-radius: 50%;
}

span.psw {
	float: right;
	padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
</head>

<body>

<div class="container-fluid bg">
<div class="row">
<div class="col-md-5 col-sm-4 col-xs-12">

<form class= "form-container" action="validate" method="post">
	<h2>Sign In</h2>
	<a href="signup">New to this site? Sign Up</a><br>
	
		<div class="imgcontainer">
			<img src="resources/images/avatar1.png" alt="Avatar" class="avatar">
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1"><b>Username / Email id</b></label> 
			<input class="form-control" id="exampleInputEmail1" type="email" placeholder="Enter Email" name="mailid" required><br>

			<label for="exampleInputPassword1"><b>Password</b></label> 
			<input class="form-control" id="exampleInputPassword1" type="password" placeholder="Enter Password" name="psw" required>
		</div>

			<button type="submit">Sign In</button>
			
			<div>
				<label> <input type="checkbox" checked="checked" name="remember"> Remember me </label>
				<span class="psw">Forgot <a href="#"> password?</a></span>
			</div>
			<div class="container" style="background-color: white">
			<button type="reset" class="btn btn-info btn-sm" id="cancel">Cancel</button></div>
	</form>

	</div>
	<div class="col-md-4 col-sm-4 col-xs-12"></div>
	</div>
	</div>
</body>
</html>
