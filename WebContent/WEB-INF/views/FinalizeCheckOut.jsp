<%@page import="net.codejava.controller.MainController"%>
<%@page import="net.codejava.model.Category"%>
<%@page import="net.codejava.service.CategoryService"%>
<%@page import="net.codejava.service.ProductTypeService"%>
<%@page import="net.codejava.model.ProductType"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Manager</title>
<style>
.solidBot {
	border-bottom: solid 2px;
	padding: 25px 10px 25px 10px;
}

td {
	vertical-align: middle !important;
}

th {
	vertical-align: middle !important;
}

.quantity {
	float: left;
	margin-right: 15px;
	background-color: #eee;
	position: relative;
	width: 80px;
	overflow: hidden
}

.quantity input {
	margin: 0;
	text-align: center;
	width: 15px;
	height: 15px;
	padding: 0;
	float: right;
	color: #000;
	font-size: 20px;
	border: 0;
	outline: 0;
	background-color: #F6F6F6
}

.quantity input.qty {
	position: relative;
	border: 0;
	width: 100%;
	height: 40px;
	padding: 10px 25px 10px 10px;
	text-align: center;
	font-weight: 400;
	font-size: 15px;
	border-radius: 0;
	background-clip: padding-box
}

.quantity .minus, .quantity .plus {
	line-height: 0;
	background-clip: padding-box;
	-webkit-border-radius: 0;
	-moz-border-radius: 0;
	border-radius: 0;
	-webkit-background-size: 6px 30px;
	-moz-background-size: 6px 30px;
	color: #bbb;
	font-size: 20px;
	position: absolute;
	height: 50%;
	border: 0;
	right: 0;
	padding: 0;
	width: 25px;
	z-index: 3
}

.quantity .minus:hover, .quantity .plus:hover {
	background-color: #dad8da
}

.quantity .minus {
	bottom: 0
}

.shopping-cart {
	margin-top: 20px;
}
</style>
<!-- CSS only -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="body" align="center">

		<div class="navHead" style="position: fixed; top: 0; left: 0;z-index: 999; width: 100%;">
			<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a
				class="navbar-brand" href="/CustomerManager/">Home</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<%
						List<ProductType> listProductType = (List<ProductType>) request.getAttribute("listProductTypes");
					for (int i = 0; i < listProductType.size(); i++) {
						String listCategories = "Listcategory" + i;
						List<Category> listCategory = (List<Category>) request.getAttribute(listCategories);
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <%
 	out.print(listProductType.get(i).getProducttype());
 %>
					</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">

							<a class="dropdown-item"
								href="<%=request.getContextPath()%>/prodType?id=<%=listProductType.get(i).getId()%>">
								Toàn bộ <%
								out.print(listProductType.get(i).getProducttype());
							%>
							</a>
							<div class="dropdown-divider"></div>

							<%
								for (Category x : listCategory) {
							%><a class="dropdown-item"
								href="<%=request.getContextPath()%>/prodCate?id=<%=x.getIdcategory()%>">
								<%
									out.print(x.getCategoryname());
								%>
							</a>
							<%
								}
							%>

						</div></li>
					<%
						}
					%>


				</ul>
				<form class="form-inline my-2 my-lg-0" method="get" action="search">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search"
						name="keyword">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/shoppingCart">Giỏ
							Hàng</a></li>
					<c:if test=" ${not empty user.username}">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> Xin chào: ${user.username}</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="">Quản lý tài khoản </a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="">Đăng xuất</a>
							</div></li>
					</c:if>

					<c:if test="${empty user.username}">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/Login">Login</a></li>
					</c:if>
				</ul>
			</nav>
		</div>

		<div style="width: 80%"text-align:center;">
			<form:form action="FinalCheckOut" method="post"
				modelAttribute="Customer">
				<div class="row" style="padding-top: 8%">
					<div class="col-lg-8 col-md-12" style="background-color: #F5F5F5">

						<br />
						<h2>Your information</h2>
						<small id="emailHelp" class="form-text text-muted "
							style="align: center;">Make sure it's the true
							information</small> <br />
						<div class="form-group row">

							<label for="example-text-input" class="col-2 col-form-label">Full
								Name:</label>
							<div class="col-10">
								<form:input type="text" class="form-control"
									id="example-text-input" aria-describedby="emailHelp"
									placeholder="John Wick" path="name" required="required" />
							</div>
						</div>
						<div class="form-group row">
							<label for="exampleInputEmail1" class="col-2 col-form-label">Email
								address:</label>
							<div class="col-10">
								<form:input type="email" class="form-control"
									id="exampleInputEmail1" aria-describedby="emailHelp"
									placeholder="name@example.com" path="email" required="required"
									pattern="{1,20}" />

							</div>

						</div>
						<div class="form-group row">
							<label for="example-tel-input" class="col-2 col-form-label">Phone
								Number:</label>
							<div class="col-10">
								<form:input type="text" class="form-control"
									id="example-tel-input" placeholder="0936119596" path="phone"
									required="required" pattern="[0-9]{8,20}" />

							</div>
						</div>
						<div class="form-group row">
							<label for="exampleFormControlTextarea2"
								class="col-2 col-form-label ">Address:</label>
							<div class="col-10">
								<form:textarea class="form-control rounded-0 md-textarea"
									placeholder="D'Capitale, Trung Hoa, Cau Giay, Ha Noi"
									type="text" id="exampleFormControlTextarea2" rows="2"
									path="address" required="required" />
							</div>
						</div>



						<hr>

						<div class="card-body"
							style="border: 2px solid gray; padding: 10px;">
							<h2>Your Cart</h2>
							<c:if test="${empty Cart or empty Cart.cartLines }">

								<h3>Your cart is empty</h3>
								<a type="button" class="btn btn-info "
									href="${pageContext.request.contextPath}/Shop">Back to
									Shopping</a>
							</c:if>
							<br />
							<!-- PRODUCT -->
							<c:set var="SubTotal" value="${0}" />
							<c:forEach items="${Cart.cartLines}" var="cartLineInfo"
								varStatus="varStatus">
								<c:set var="SubTotal" value="${SubTotal + cartLineInfo.amount}" />
								<div class="row">
									<div class="col-12 col-sm-12 col-md-2 text-center">
										<a
											href="${pageContext.request.contextPath}/DetailProd?id=${cartLineInfo.productInfo.id}"><img
											class="img-responsive"
											src="${pageContext.request.contextPath}/productImage?id=${cartLineInfo.productInfo.id}"
											alt="prewiew" width="120" height="120"></a>

									</div>
									<div
										class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
										<h4 class="product-name">
											<strong>${cartLineInfo.productInfo.productname}</strong>
										</h4>
										<h4>
											<small>Product description</small>
										</h4>
									</div>
									<div
										class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
										<div class="col-3 col-sm-3 col-md-4 text-md-right"
											style="padding-top: 5px">
											<h6>
												<strong><fmt:formatNumber
														value="${cartLineInfo.productInfo.price}" type="currency" />
													<span class="text-muted"></span></strong>
											</h6>
										</div>
										<div class="col-4 col-sm-4 col-md-4">
											<div class="quantity">
												<a style="text-align: center; padding-top: 10px;"
													type="button" class="plus"
													href="${pageContext.request.contextPath}/plusProdCartFinal?id=${cartLineInfo.productInfo.id}">+</a>
												<input type="number" readonly
													value="${cartLineInfo.quantity}" title="Qty" class="qty"
													size="4"> <a
													style="text-align: center; padding-top: 10px;"
													type="button" value="-" class="minus"
													href="${pageContext.request.contextPath}/botSoLuongFinal?id=${cartLineInfo.productInfo.id}">-</a>
											</div>
										</div>
										<div class="col-2 col-sm-2 col-md-4 text-right">
											<a class="btn btn-danger"
												href="${pageContext.request.contextPath}/removeProdCartFinal?id=${cartLineInfo.productInfo.id}">Remove</a>
										</div>
									</div>
								</div>
								<hr>
							</c:forEach>

						</div>
					</div>


					<div class="col-md-12 col-lg-4" style="background-color: #DCDCDC">
						<div style="width: 90%; padding-top: 10%;">
							<h2 class="solidBot text-left">Summary</h2>
							<div class="solidBot text-left">
								<a style="padding-bottom: 10px">Do you have a giftcode?</a> <br>

								<div class="input-group ">
									<input type="text" class="form-control"
										placeholder="Enter your giftcode">
									<div class="input-group-append">
										<button class="btn btn-success" style="width: 100px"
											type="submit">Apply</button>
									</div>
								</div>
							</div>
							<div class="solidBot ">
								<div class="row">
									<h3 class="text-left col-6">Subtotal</h3>
									<h3 class="text-right col-6">
										<fmt:formatNumber value="${SubTotal}" type="currency" />
									</h3>
								</div>
								<div class="row" style="padding-top: 20px">
									<a class="text-left col-6">Shipping Standart </a> <a
										class="text-right col-6"> Free</a>
								</div>
								<div class="row">
									<a class="text-left col-6">Sales Tax </a> <a
										class="text-right col-6"> 0</a>
								</div>

							</div>
							<div class="solidBot">
								<div class="row">
									<h3 class="text-left col-6">Estimated total</h3>
									<h3 class="text-right col-6">
										<fmt:formatNumber value="${SubTotal}" type="currency" />
									</h3>
								</div>
							</div>
							<div style="padding: 10px;">
							
								<c:if test="${not empty Cart.cartLines }">
									<c:if test="${SubTotal > 0 }">
										<a type="button" class="btn btn-info "
									href="${pageContext.request.contextPath}/Shop">Back to
									Shopping</a>
										<c:if test="${ not empty param.name}">
											<button type="submit" class="btn btn-warning" value="save">Checkout</button>
										</c:if>

										<c:if test="${empty param.name}">
											<button type="submit" class="btn btn-warning" value="save">Checkout</button>
										</c:if>
									</c:if>
								</c:if>
							</div>
							<small id="emailHelp" class="form-text text-muted text-left"
						style="align: center;">Need help? Call us at 19008198</small>
						</div>
					</div>
				</div>
			</form:form>

		</div>
	</div>
</body>
</html>