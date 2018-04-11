<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign up</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>

.bg{
	background: url("resources/images/background2.jpg");
	height: 100%; 
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}

.container{
	width: 70%;
}

body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box
}

/* Full-width input fields */
input {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #fff;
	outline: none;
}

hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for all buttons */
button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

button:hover {
	opacity: 1;
}

/* Extra styles for the cancel button */
.cancelbtn {
	padding: 14px 20px;
	background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
	float: left;
	width: 50%;
}

/* Add padding to container elements */
.container {
	padding: 16px;
}

/* Clear floats */
.clearfix::after {
	content: "";
	clear: both;
	display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
	.cancelbtn, .signupbtn {
		width: 100%;
	}
}
</style>
</head>

<body>
<div class="container-fluid bg">
	<form action="from_form" method="post" style="border: 1px solid #ccc">
		<div class="container">
		
			<h1>Sign Up</h1>
			<a href="signin" style="float:right">Already a member? Log in</a>
			<p>Please fill in this form to create an account.</p>
			<hr>

			<label for="fullname"><b>Full Name</b></label> 
			<input class="form-control" type="text" placeholder="Enter Name" name="fullname" required> 
			
			<label for="email"><b>Email</b></label> 
			<input class="form-control" type="email" placeholder="Enter Email" name="email" required> 
			
			<label for="psw"><b>Password</b></label> 
			<input class="form-control" type="password" placeholder="Enter Password" name="psw" required> 
			
			<label for="psw-repeat"><b>Repeat Password</b></label> 
			<input class="form-control" type="password" placeholder="Repeat Password" name="psw-repeat"required> 
			
			<label for="mob"><b>Mobile number</b></label> 
			<input class="form-control" type="text" placeholder="Enter mobile no" name="mob" required>
			
			<div class="form-group">
			      <label for="sel1">Select State</label>
			      <select class="form-control" id="sel1" placeholder="Select state" name="state">
			        <option>Delhi</option>
			        <option>Goa</option>
			        <option>Jammu and Kashmir</option>
			        <option>Karnataka</option>
			        <option>Kerala</option>
			        <option>Madhya Pradeh</option>
			        <option>Maharashtra</option>
			        <option>Manipur</option>
			        <option>Mizoram</option>
			        <option>Nagaland</option>
			        <option>Odisha</option>
			        <option>Rajasthan</option>
			        <option>Tamil Nadu</option>
			        <option>Uttar Pradesh</option>
			        <option>West Bengal</option>
			      </select>
			      <br>
		      </div>

			<div class="clearfix">
				<button type="submit" class="signupbtn">Sign Up</button>
				<button type=reset class="cancelbtn">Cancel</button>
			</div>
			
			<label> 
			<input type="checkbox" checked="checked" name="remember" style="margin-bottom: 15px"> Remember me
			</label>

			<p>
				By creating an account you agree to our <a href="#" style="color: dodgerblue">Terms & Privacy</a>.
			</p>
		</div>
	</form>
	</div>
</body>
</html>