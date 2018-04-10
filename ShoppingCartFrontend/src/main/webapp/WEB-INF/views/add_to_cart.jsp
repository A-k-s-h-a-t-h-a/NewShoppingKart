<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products selected by me</title>
</head>

<body>
	<div class="container">
		<form action="cart/add/" method="post">
			<img alt="" src="${selectedProductImage}"><br><br>
			<p>
				${selectedProduct.name}<br>
				${selectedProduct.price}
			</p>
			<c:if test="${ifLoggedIn== true}">
				<a href="cart/add/${selectedProduct.product_id}">Add To Cart</a>
			</c:if>
		</form>
	</div>
</body>
</html>