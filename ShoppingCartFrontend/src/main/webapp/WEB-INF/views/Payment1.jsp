<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
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
	<div class="container">
		<form action="" method="post">
			<div class="form-group">


    <fieldset>
    <legend>Contact details</legend>
        <ol>
            <li>
                <label for=name>Name</label>
                <input id=name name=name type=text placeholder="First and last name" required />
            </li>
            <li>
                <label for=email>Email id</label>
                <input id=email name=email type=email placeholder="Email id" required />
            </li>
             <li>
                <label for=phone>Mobile</label>
                <input id=phone name=phone type=tel placeholder="Eg. +14445556666" required />
            </li>
        </ol>
    </fieldset>
    
    <fieldset>
        <legend>Delivery address</legend>
        <ol>
            <li>
                <label for=address>Address</label>
                <input id=address name=address rows=5 required>
            </li>
            <li>
                <label for=postcode>Pin Code</label>
                <input id=postcode name=postcode type=text required />
            </li>
        </ol>
    </fieldset>
</div>
</form>
</div>

</body>
</html>