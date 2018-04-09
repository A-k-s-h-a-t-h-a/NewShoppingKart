<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Navigation Bar</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
    var button = document.getElementById('show_button')
    button.addEventListener('click',hideshow,false);

    function hideshow() {
        document.getElementById('hidden-div').style.display = 'block'; 
        this.style.display = 'none'
    }   
</script>
</head>

<body style="padding-top:75px">

	<nav class="navbar navbar-inverse navbar-fixed-top">
	  <div class="container-fluid">
	  
	    <div class="navbar-header" action="/" method="get">
	      <a class="navbar-brand" href="/">Shopping Kart</a>
	    </div>
	   
	    	<ul class="nav navbar-nav navbar-left">
		      <li><a href="signin"><button class="btn btn-success navbar-btn"  id="show_button"><span class="glyphicon glyphicon-log-in"></span> Log in</button></a></li>
		      <li><a href="signup"><button class="btn btn-warning navbar-btn" id="show_button"><span class="glyphicon glyphicon-user"></span> Sign Up</button></a></li> 
		    </ul>
	
		    <ul class="nav navbar-nav navbar-right">
		      <c:if test= "${ifLoggedIn==true}">
			      <li><a href="mycart"><button class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-shopping-cart"></span> My Cart(${size })</button></a></li>
			      <li><a href="signout"><button class="btn btn-danger navbar-btn"><span class="glyphicon glyphicon-log-out"></span> Log out</button></a></li>
		      </c:if>
		    </ul>
		    
		    <ul class="nav navbar-nav navbar-right">
		      <c:if test="${Registered==true}">
			      <li><a href="mycart"><button class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-shopping-cart"></span> My Cart(${size })</button></a></li>
			      <li><a href="signout"><button class="btn btn-danger navbar-btn"><span class="glyphicon glyphicon-log-out"></span> Log out</button></a></li>
		      </c:if>
		    </ul>
	
	    <form class="navbar-form navbar-left" action="">
	      <div class="input-group">
	        <input type="text" class="form-control" placeholder="Search" name="search">
	        <div class="input-group-btn">
	          <button class="btn btn-default" type="submit">
	            <i class="glyphicon glyphicon-search"></i>
	          </button>
	        </div>
	      </div>
	    </form>
	    		
	  </div>
	</nav>

</body>
</html>