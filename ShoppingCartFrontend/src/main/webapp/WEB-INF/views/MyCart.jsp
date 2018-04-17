<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Cart</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

	${noItems}

			<div class="container">
				<table class="table table-striped table-bordered">
				<tr style="text-align:center">
					<td><h4>Image</h4></td>
					<td><h4>Name</h4></td>
					<td><h4>Price</h4></td>
					<td><h4>Quantity</h4></td>
					<td><h4>Action</h4></td>
				</tr>
				
				<c:forEach var="a" items="${cartList}">
					<tr style="text-align:center">
						<td><img alt="" src="resources/images/ShoppingCartImages/${a.productID}.png"></td>
						<td>${a.productName}</td>
						<td>${a.price}</td>
						<td><a href="editcartqty/${a.id}"><button class="btn btn-primary">+</button></a></td>
						<td><a href="remove/?id=${a.id}"><button type="button" class="btn btn-danger btn-sm">Delete</button></a><br></td>
					</tr>
				</c:forEach>
				</table>
				
				<a href="buy"><button class="img-responsive pull-right btn-default">Buy</button></a>
			</div>
</body>
</html>