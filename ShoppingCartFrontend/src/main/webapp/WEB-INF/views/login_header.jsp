<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	
	    <ul class="nav navbar-nav navbar-right">
	      <c:if test= "${ifLoggedIn==true}">
		      <li><a href="home">Home</a></li>
		      <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
		      <li><a href="mycart"><span class="glyphicon glyphicon-shopping-cart"></span> My Cart(${size })</a></li>
	      </c:if>
	    </ul>
	    
	    <ul class="nav navbar-nav navbar-left">
	      <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Log in</a></li>
	      <li><a href="register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> 
	    </ul>
	  </div>
	</nav>
</body>
</html>