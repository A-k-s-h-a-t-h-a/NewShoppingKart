<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home page of Shopping Kart</title>
<link rel="stylesheet" href="resources/css-styles/home.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
	
	
	<c:if test="${sinceUserClickedMyCart== true}">
	<jsp:include page="MyCart.jsp"></jsp:include>
	</c:if>
	<c:if test="${isUserSelectedProduct}">
	<jsp:include page="add_to_cart.jsp"></jsp:include>
	</c:if>
	
	
<%-- 	<c:if test="${didUserSearchProducts== true}"> --%>
<%-- 		<jsp:include page="add_to_cart.jsp"></jsp:include> --%>
<%-- 	</c:if> --%>
	
	
	<c:if test="${sinceUserClickedBuy== true}">
		<jsp:include page="Shipping.jsp"></jsp:include>
	</c:if>
	<c:if test="${proceedingToPayment== true}">
		<jsp:include page="Payment.jsp"></jsp:include>
	</c:if>
	<c:if test="${orderPlaced== true}">
		<jsp:include page="Confirmed.jsp"></jsp:include>
	</c:if>
	
	
	
	<c:if test="${brandLogoClicked== true}">
		<jsp:include page="login_header.jsp"></jsp:include>
	</c:if>
	
	<c:if test="${carouselDisplayedOnce== true}">
		<c:if test="${isAdmin!= true}">
			<c:if test="${didUserSearchProducts!= true}">
				<c:if test="${isUserSelectedProduct!= true}">
					<div class="container"><br>
						<div id="myCarousel" class="carousel slide" data-ride="carousel">
						    <!-- Indicators -->
						    <ol class="carousel-indicators">
						      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						      <li data-target="#myCarousel" data-slide-to="1"></li>
						      <li data-target="#myCarousel" data-slide-to="2"></li>
						      <li data-target="#myCarousel" data-slide-to="3"></li>
						    </ol>
						
						    <!-- Wrapper for slides -->
						    <div class="carousel-inner">
						    
						    	<div class="item active">
						        	<img src="resources\images\offer1_watches.jpg" alt="Offer on watches" width="460" height="345">
						      	</div>
						
						      	<div class="item">
						        	<img src="resources\images\offer3_bags.jpg" alt="Offer on bags" width="460" height="345">
						      	</div>
						
						      	<div class="item">
						        	<img src="resources\images\offer4_mobiles.jpg" alt="Offer on mobiles" width="460" height="345">
						      	</div>
						
						    </div>
						
						    <!-- Left and right controls -->
						    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
						      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						      <span class="sr-only">Previous</span>
						    </a>
						    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
						      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						      <span class="sr-only">Next</span>
						    </a>
						    
						</div>
					</div><br><br>
					
					<div class="container">
						<div class="row">
							<c:forEach items="${productList}" var="p"> 
								<div class="col-md-4 img-thumbnail">
									<br><br><a href ="product/get/${p.product_id}"><img src="resources/images/${p.product_id}.png" width="200px" height="200px"></a><br><br>
									${p.name}
									
									<div class="caption">
										<c:if test="${pageContext.request.userPrincipal.name!=null}">
											<a href="cart/add/${p.product_id}"><button class="btn btn-warning btn-sm">Add To Cart</button></a>
										</c:if>
										
										<a href="buy?buyreq=prpage&prid=${p.product_id}"><button class="btn btn-primary btn-sm">Buy</button></a>
										
									</div>
								</div>
							</c:forEach>
						</div><br>
					</div>
									</c:if>
								</c:if>
							</c:if>
						</c:if>
	
	
	<br>
	 ${fail}
	 ${forbidden}
	
	 
</body>
</html>