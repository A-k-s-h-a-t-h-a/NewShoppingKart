<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home page of Shopping Kart</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

.carousel-inner > .item > img, .carousel-inner > .item > a > img {
    width: 100%;
    margin: auto;
  }
</style>
</head>

<body>
	<center>
		<h1>Welcome to Shopping Kart</h1>
		${logoutmessage }	${success} 
	</center>
	<br>
	
	<jsp:include page="login_header.jsp"></jsp:include>
	<jsp:include page="bootstrap_product_menu.jsp"></jsp:include>
	<hr color="blue" size="4">


	<c:if test="${isAdmin== true}">
		<jsp:include page="admin/adminhome.jsp"></jsp:include>
	</c:if>
	<c:if test="${sinceUserClickedSignIn== true}">
		<jsp:include page="SignIn.jsp"></jsp:include>
	</c:if>
	<c:if test="${sinceUserClickedSignUp== true}">
		<jsp:include page="SignUp.jsp"></jsp:include>
	</c:if>
	
	
	<c:if test="${brandLogoClicked== true}">
		<jsp:include page="login_header.jsp"></jsp:include>
	</c:if>
	
	<c:if test="${carouselDisplayedOnce== true}">
		<c:if test="${isAdmin!= true}">
			<c:if test="${didUserSearchProducts!= true}">
				<c:if test="${isUserSelectedProduct!= true}">
					<jsp:include page="carousels.jsp"></jsp:include>
				</c:if>
			</c:if>
		</c:if>
	</c:if>
	
	
	<c:if test="${sinceUserClickedMyCart== true}">
	<jsp:include page="MyCart.jsp"></jsp:include>
	</c:if>
	<c:if test="${isUserSelectedProduct}">
	<jsp:include page="add_to_cart.jsp"></jsp:include>
	</c:if>
	
	
	<c:if test="${didUserSearchProducts== true}">
		<jsp:include page="add_to_cart.jsp"></jsp:include>
	</c:if>
	
	
	<c:if test="${sinceUserClickedBuy== true}">
		<jsp:include page="Shipping.jsp"></jsp:include>
	</c:if>
	<c:if test="${proceedingToPayment== true}">
		<jsp:include page="Payment.jsp"></jsp:include>
	</c:if>
	<c:if test="${orderPlaced== true}">
		<jsp:include page="Confirmed.jsp"></jsp:include>
	</c:if>
	
	<br>
	 ${fail}
	 
</body>
</html>