<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign up</title>
<link rel="stylesheet" href="signINstyles.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="css/global.css" type="text/css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>

.bg{
	background: url("resources/images/background4.jpg");
	height: 100%; 
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}

form.container{
	border:1px solid #fff; 
	padding:50px 60px; 
	margin-top:20vh;
	-webkit-box-shadow: -1px 4px 26px 11px rgba(0,0,0,0.75);
	-moz-box-shadow:  -1px 4px 26px 11px rgba(0,0,0,0.75);
	box-shadow:  -1px 4px 26px 11px rgba(0,0,0,0.75);
}

a{
	float:right
}

body {
	font-family: Arial, Helvetica, sans-serif;
}

form {
	border: 1px solid #ccc;
}

input {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background:#000;
	color:#fff;
	filter:alpha(opacity=60);
	opacity:0.6;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #fff;
	outline: none;
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

.clearfix::after {
	content: "";
	clear: both;
	display: table;
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
		<div class="col-md-3"></div>
		<div class="col-md-6 col-sm-4 col-xs-12">
		
			<form class= "form-container" action="j_spring_security_check" method="post">
				<h2>Sign In</h2>
				<a href="signup" style="color: darkblue">New to this site? Sign Up</a><br>
				
					<div class="imgcontainer">
						<img src="resources/images/avatar1.png" alt="Avatar" class="avatar">
					</div>
			
					<div class="form-group">
						<label for="exampleInputEmail1"><b>Username / Email id</b></label> 
						<input class="form-control" id="exampleInputEmail1" type="text" placeholder="Enter Email" name="mailid" required>
			
						<label for="exampleInputPassword1"><b>Password</b></label> 
						<input class="form-control" id="exampleInputPassword1" type="password" placeholder="Enter Password" name="psw" required><br><br>
					
						<div class="clearfix">
							<button type="submit" class="btn btn-info btn-md">Sign In</button>
							<button type="reset" class="btn btn-danger btn-md">Cancel</button>		
						</div>	
					
					</div>
					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		
		</div>
		<div class="col-md-3"></div>
	</div>
</div>
</body>
</html>
