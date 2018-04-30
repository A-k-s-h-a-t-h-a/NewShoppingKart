<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Body</title>
</head>

<body>	

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
	        <img src="resources\images\offer2_shoes.jpg" alt="Offer on shoes" width="460" height="345">
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
	</div>

<div class="container">

<div class="row">
<c:forEach items="${productList}" var="p"> 
<div class="col-md-4">
			<a href ="product/get/${p.product_id}"><img src="resources\images\${p.product_id}.png" width="200px" height="200px"></a>
			</div>
			</c:forEach>
		</div>
</div>

	<div class="container">	<br><br>
		<div> <legend>Kids Toys</legend>
			<a href ="cart/add/Kid-1"><img src="resources\images\Kid-1.png" width="200px" height="200px"></a>
			<a href ="cart/add/Kid-02"><img src="resources\images\Kid-02.png" width="200px" height="200px"></a>
		</div><br><br>
		<div> <legend>Watches</legend>
			<a href ="cart/add/Men-02"><img src="resources\images\Men-02.png" width="200px" height="200px"></a>
			<a href ="cart/add/Wom-1"><img src="resources\images\Wom-1.png" width="200px" height="200px"></a>
		</div><br><br>
		<div> <legend>Laptops</legend>
			<a href ="cart/add/El-1"><img src="resources\images\El-1.png" width="200px" height="150px"></a>
			<a href ="cart/add/El-2"><img src="resources\images\El-2.png" width="200px" height="150px"></a>
		</div><br><br>
		<div> <legend>Books</legend>
			<a href ="cart/add/Pau-02"><img src="resources\images\Pau-02.png" width="200px" height="300px"></a>
			<a href ="cart/add/Kha-03"><img src="resources\images\Kha-03.png" width="200px" height="300px"></a>
		</div><br><br>
		<div> <legend>Shoes</legend>
			<a href ="cart/add/Men-03"><img src="resources\images\Men-03.png" width="200px" height="200px"></a>
			<a href ="cart/add/Wom-3"><img src="resources\images\Wom-3.png" width="200px" height="200px"></a>
		</div><br><br>
		<div> <legend>Sports</legend>
			<a href ="cart/add/Spo-01"><img src="resources\images\Spo-01.png" width="200px" height="200px"></a>
			<a href ="cart/add/Spo-02"><img src="resources\images\Spo-02.png" width="200px" height="200px"></a>
		</div>
	</div>
</body>
</html>