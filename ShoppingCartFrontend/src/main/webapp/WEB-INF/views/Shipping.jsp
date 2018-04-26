<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Payment</title>
<link rel="stylesheet" href="resources/css-styles/shipping.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
			    
			    <button class="animate pull-right">Proceed To Pay</button></a>
			</div>
		</form:form>
	</div>
	
</body>
</html>
