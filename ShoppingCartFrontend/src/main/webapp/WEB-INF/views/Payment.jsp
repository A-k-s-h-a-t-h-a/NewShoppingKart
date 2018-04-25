<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="paymentstyles.css">
<title>Payment page</title>
<style>

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
<div class="container">
	<div class="col-md-3"></div>
		<div class="col-md-8 col-sm-4 col-xs-12">
		
	<legend>Order Summary</legend>
		<table class="table table-striped table-bordered table-hover">
			<tr style="text-align:center">
				<p>Order no:		 ${id} </p><br>
				<p>Name:			 ${name} </p><br>
				<p><c:forEach var="u" items="${usercart}">
				Product Ordered: ${u.productName}
				Quantity:		 ${u.quantity}
				Sub Total:		 ${u.subtotal}
				</c:forEach></p>
				<p>Grand Total:	 ${grandTotal} </p><br>
				<p>Shipping Address:${shippingAddress} </p><br>
				<p>Pin Code:		 ${pincode} </p><br>
			</tr>
		</table>
	
	
		<form action="pay" method="post">
			<div class="row">
			<!-- I'm making it full width on <= small devices and 4/12 page width on >= medium devices -->
				<div class="col-xs-12 col-md-8">

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
							<form role="form" id="payment-form">
							
								<div class="row">
									<div class="col-xs-7 col-md-7">
										<div class="form-group">
											<label name="grandTotal"><span class="hidden-xs">PAYMENT AMOUNT</span></label>
										</div>
									</div>
									<div class="col-xs-7 col-md-4 pull-right">
										<div class="form-group">
											<input name="grandTotal" type="text" class="form-control" value="Rs ${grandTotal}" disabled="disabled"/>
										</div>
									</div> <br>
								</div>
						
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label>NAME ON CARD</label> 
											<input name="nameOnCard" type="text" class="form-control" required="true"/>
										</div>
									</div>
								</div>
						
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label>CARD NUMBER</label>
											<div class="input-group">
												<input type="text" class="form-control" name="cardNumber"
															placeholder="Valid Card Number" autocomplete="cc-number" required autofocus /> 
													<span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
											</div>
										</div>
									</div>
								</div>
							
								<div class="row">
									<div class="col-xs-7 col-md-7">
										<div class="form-group">
											<label>
												<span class="hidden-xs">EXPIRY</span>
												<span class="visible-xs-inline">EXP</span> DATE</label> 
												<input type="text" class="form-control" name="cardExpiry" 
														placeholder="MM / YY" required />
										</div>
									</div>
									<div class="col-xs-5 col-md-5 pull-right">
										<div class="form-group">
											<label >CVV/CVC</label> 
											<input type="text" class="form-control" name="cardCVC" 
														placeholder="CVV" required />
										</div>
									</div>
								</div>
							
								<div class="row">
									<div class="col-xs-12">
										<i class="icon-lock"></i><button class="btn btn-success btn-lg btn-block" type="submit">Pay Rs ${grandTotal}</button>
									</div>
								</div>
							
							</form>
						</div>
					</div>
				<!-- CREDIT CARD FORM ENDS HERE -->
				</div>
			</div>
		</form>
	</div>
	</div>

</body>
</body>
</html>