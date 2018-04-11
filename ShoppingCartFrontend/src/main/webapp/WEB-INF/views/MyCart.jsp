<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Cart</title>
</head>

<body>
	${noItems}
	
	<c:if test="${cartDetails== true}">
		<c:forEach var="cart" items="${cartList}">
			<div class="container">
				<table class="table table-bordered">
					
					<img alt="" src="resources/images/ShoppingCartImages/${cart.productID}.png">
				<tr>
					<td><h4>Name</h4></td>
					<td><h4>Price</h4></td>
					<td><h4>Quantity</h4></td>
				</tr>
				<tr>
					<td><input type="text" name="productName" value="${cart.productName}"></td>
					<td><input type="text" name="price" value="${cart.price}"></td>
					<td><input type="text" name="quantity" value="${cart.quantity}"></td>
					<td><a href="buy">BUY</a><br></td>
				</tr>
				</table>
			</div>
		</c:forEach>
	</c:if>

</body>
</html>