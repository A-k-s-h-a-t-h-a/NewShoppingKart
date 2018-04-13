<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	margin: 50px auto;
	width: 600px;
}

/* CSS for Credit Card Payment form */
.credit-card-box .panel-title {
	display: inline;
	font-weight: bold;
}

.credit-card-box .form-control.error {
	border-color: red;
	outline: 0;
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
		rgba(255, 0, 0, 0.6);
}

.credit-card-box label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}

.credit-card-box .payment-errors {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}

.credit-card-box label {
	display: block;
}
/* The old "center div vertically" hack */
.credit-card-box .display-table {
	display: table;
}

.credit-card-box .display-tr {
	display: table-row;
}

.credit-card-box .display-td {
	display: table-cell;
	vertical-align: middle;
	width: 50%;
}
/* Just looks nicer */
.credit-card-box .panel-heading img {
	min-width: 180px;
}
</style>
</head>

<body>

	Order Sumamry
	Product ordered: 
	Quantity: 
	Ordered by:
	Delivered to: 
	Total Amount:

	<div class="container">
		<form:form action="pay" method="post" modelAttribute="payment">
			<div class="row">
			<!-- I'm making it full width on <= small devices and 4/12 page width on >= medium devices -->
				<div class="col-xs-12 col-md-4">

				<!-- CREDIT CARD FORM STARTS HERE -->
					<div class="panel panel-default credit-card-box">
						<div class="panel-heading display-table">
							<div class="row display-tr">
								<h3 class="panel-title display-td">Payment Details</h3>
								<div class="display-td">
									<img class="img-responsive pull-right" src="http://i76.imgup.net/accepted_c22e0.png">
								</div>
							</div>
						</div>
						<div class="panel-body">
							<form:form role="form" id="payment-form">
							
								<div class="row">
									<div class="col-xs-7 col-md-7">
										<div class="form-group">
											<form:label path="amount"><span class="hidden-xs">PAYMENT AMOUNT</span></form:label>
											<form:input path="amount" type="text" class="form-control" name="amount" value="${amount}" disabled="disabled"/>
										</div>
									</div>
		<!-- 							<div class="col-xs-3 col-md-3 pull-right"> -->
		<!-- 								<div class="form-group"> -->
		<!-- 									<br> -->
		<!-- 									<a href="edit"><button class="btn btn-default">EDIT</label> </a></button> -->
		<!-- 								</div> -->
		<!-- 							</div> -->
								</div>
						
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<form:label >NAME ON CARD</form:label> 
											<form:input type="text" class="form-control" name="nameOnCard" required />
										</div>
									</div>
								</div>
						
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<form:label >CARD NUMBER</form:label>
											<div class="input-group">
												<form:input  type="tel" class="form-control" name="cardNumber"
															placeholder="Valid Card Number" autocomplete="cc-number" required autofocus /> 
													<span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
											</div>
										</div>
									</div>
								</div>
							
								<div class="row">
									<div class="col-xs-7 col-md-7">
										<div class="form-group">
											<form:label >
												<span class="hidden-xs">EXPIRY</span>
												<span class="visible-xs-inline">EXP</span> DATE</form:label> 
											<form:input  type="tel" class="form-control" name="cardExpiry" 
														placeholder="MM / YY" autocomplete="cc-exp" required />
										</div>
									</div>
									<div class="col-xs-5 col-md-5 pull-right">
										<div class="form-group">
											<form:label >CVV</form:label> 
											<form:input type="tel" class="form-control" name="cardCVC" 
														placeholder="CVV" autocomplete="cc-csc" required />
										</div>
									</div>
								</div>
							
								<div class="row">
									<div class="col-xs-12">
										<i class="icon-lock"></i><button class="btn btn-success btn-lg btn-block" type="submit">Pay ${amount}</button>
									</div>
								</div>
							
							</form:form>
						</div>
					</div>
				<!-- CREDIT CARD FORM ENDS HERE -->
				</div>
			</div>
		</form:form>
	</div>

	<!-- If you're using Stripe for payments -->
	<script type="text/javascript" src="https://js.stripe.com/v2/"></script>

</body>
</body>
</html>