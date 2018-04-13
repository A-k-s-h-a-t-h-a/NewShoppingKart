<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}
</style>]

</head>

<body>
<script type="text/javascript">
document.getElementById("pimage").value="hfds";
</script>
	<div class="container">
		<form action="product/save/" method="post" enctype="multipart/form-data">
			<table class="table table-bordered">
				<c:if test="${!empty selectedProduct.product_id}">
					<input type="hidden" name='product_id' value="${selectedProduct.product_id}" />
				</c:if>
				<c:if test="${empty selectedProduct.product_id}">
					<tr>
						<td>ID</td>
						<td><input id="textfield1" type="text" name='product_id' required/></td>
					</tr>
				</c:if>

				<tr>
					<td>Name</td>
					<td><input id="textfield2" type="text" name='name' value="${selectedProduct.name}" required></td>
				</tr>
	
				<tr>
					<td>Description</td>
					<td><input id="textfield3" type="text" name='description' value="${selectedProduct.description}"></td>
				</tr>
	
				<tr>
					<td>Price</td>
					<td><input id="textfield4" type="text" name='price' value="${selectedProduct.price}" required></td>
				</tr>
				
				<tr>
					<td>Stock</td>
					<td><input id="textfield5" type="text" name='stock' value="${selectedProduct.stock}" required></td>
				</tr>
	
				<tr>
					<td>Select Category</td>
					<td>
					
						<select name="category_id">
						<option value="${selectedProduct.cat_id}"> Select Category </option>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.category_id }"> ${category.name} </option>
							</c:forEach>
						</select>
					
					</td>
				</tr>
				
				<tr>
					<td>Select Supplier</td>
					<td>
						<select name="supplier_id">
						<option value="${selectedProduct.sup_id}"> Select supplier </option>
							<c:forEach var="supplier" items="${supplierList}">
								<option value="${supplier.supplier_id }"> ${supplier.name} </option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
									
			<input id="pimage" type="file" name="file" >	<br>
			<input type="submit" value='Submit Product'>
			<input type="reset" value="Cancel" /><br><br><br><br>
		</form>
	</div>
	
	${productsuccess}
	${producterror}
	${uploadmsg}
	
	<div class="container">
		<table class="table table-striped table-bordered table-hover">
				<tr style="text-align:center">
					<td><h4>Product ID</h4></td>
					<td><h4>Product Name</h4></td>
					<td><h4>Product Description</h4></td>
					<td><h4>Product Price</h4></td>
					<td><h4>Product Stock</h4></td>
					<td><h4>Category ID</h4></td>
					<td><h4>Supplier ID</h4></td>
					<td><h4>Image</h4></td>
					<td><h4>Action</h4></td>
				</tr>
				<c:forEach var="product" items="${productList}">
					<tr style="text-align:center">
						<td>${product.product_id} </td>
						<td>${product.name} </td>
						<td>${product.description} </td>
						<td>${product.price} </td>
						<td>${product.stock} </td>
						<td>${product.cat_id} </td>
						<td>${product.sup_id} </td>
						<td><img src="resources/images/ShoppingCartImages/${product.product_id}.png" height="100px" width="75px"> </td>
						<td>
			
								<a href="product/delete/?id=${product.product_id}"><button type="button" class="btn btn-danger btn-sm">Delete</button></a> | 
								<a href="product/edit/?id=${product.product_id}"><button type="button" class="btn btn-info btn-sm">Edit</button></a>
							
						</td>
					</tr>
				</c:forEach>
		</table>
	</div>
</body>
</html>