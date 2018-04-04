<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>This is home page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>

<body>
	<center>
		<h1>Welcome to shopping cart</h1>
		${logoutmessage }	${success} 
	</center>
	<br>
	
	<jsp:include page="login_header.jsp"></jsp:include>
	<jsp:include page="bootstrap_product_menu.jsp"></jsp:include>
	<hr color="blue" size="4">


	<c:if test="${isAdmin== true}">
		<jsp:include page="admin/adminhome.jsp"></jsp:include>
	</c:if>

	<c:if test="${sinceUserClickedLogin== true}">
		<jsp:include page="Login.jsp"></jsp:include>
	</c:if>

	<c:if test="${sinceUserClickedRegister== true}">
		<jsp:include page="Registration.jsp"></jsp:include>
	</c:if>
	
	
	<c:if test="${isUserClickedMyCart== true}">
	<jsp:include page="Cart.jsp"></jsp:include>
	</c:if>
	
	<c:if test="${isUserSelectedProduct}">
	<jsp:include page="selected_product.jsp"></jsp:include>
	</c:if>
	
	<br> ${fail}
</body>
</html>