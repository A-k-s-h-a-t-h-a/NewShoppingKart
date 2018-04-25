<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
<link rel="stylesheet" href="paymentstyles.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
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

hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for all buttons */
#s1 {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	opacity: 0.9;
}

#s1:hover {
	opacity: 1;
}

/* Add padding to container elements */
.container {
	padding: 16px;
	width: 75%;
}

/* Clear floats */
.clearfix::after {
	content: "";
	clear: both;
	display: table;
}
</style>
</head>

<body>
	<div class="container">
		<form:form action="proceed" method="post" modelAttribute="payment">
			<div class="form-group">

			    <fieldset>
				    <legend>Contact details</legend>
				        <ol>
				            <li>
				                <form:label path="name">Name</form:label>
               					<form:input path="name" type="text" required="true" />
				            </li>
				             <li>
				                <form:label path="mobile">Mobile</form:label>
               					<form:input path="mobile" type="text" required="true" />
				            </li>
				            <li>
				                <form:label path="quantity">Quantity</form:label>
               					<form:input path="quantity" type="number" required="true" />
				            </li>
				        </ol>
			    </fieldset>
			    
			    <fieldset>
			        <legend>Delivery address</legend>
			        <ol>
			            <li>
			                 <form:label path="shippingAddress">Address</form:label>
               				 <form:input path="shippingAddress" type="text" required="true" />
			            </li>
			            <li>
			                 <form:label path="pincode">Pin Code</form:label>
               				 <form:input path="pincode" type="number" required="true" />
			            </li>
			        </ol>
			    </fieldset>
			    
			    <button id="s1" class="btn btn-md pull-right">Proceed To Pay</button></a>
			</div>
		</form:form>
	</div>
	
</body>
</html>
