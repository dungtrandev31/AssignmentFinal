<%@page import="net.codejava.controller.MainController"%>
<%@page import="net.codejava.model.Category"%>
<%@page import="net.codejava.service.CategoryService"%>
<%@page import="net.codejava.service.ProductTypeService"%>
<%@page import="net.codejava.model.ProductType"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shop</title>
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
<script type="text/javascript">
	$(document).ready(function() {
		$('.toast').toast('show');
	});

	$(document).on('show.bs.modal', '.modal', function () {
	    var zIndex = 1040 + (10 * $('.modal:visible').length);
	    $(this).css('z-index', zIndex);
	    setTimeout(function() {
	        $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
	    }, 0);
	});
</script>
<body>
	<div class="body" align="center">
		<c:if test="${not empty message }">
			<div role="alert" aria-live="assertive" aria-atomic="true"
				class="toast float-left" data-autohide="false"
				style="position: fixed; top: 70px; right: 10px; z-index: 999; width: 100%;"
				data-delay="4000">
				<div class="toast-header">
					<strong class="mr-auto">Notice</strong> <small>now</small>
					<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="toast-body">${message }</div>
			</div>
		</c:if>



		<div class="navHead"
			style="position: fixed; top: 0; left: 0; z-index: 999; width: 100%;">
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
						placeholder="Search" aria-label="Search" name="keyword">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/shoppingCart">Giỏ
							Hàng</a></li>



					<c:choose>
						<c:when test="${empty cookieValue.value}">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/Login">Login</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> Xin chào: ${cookieValue.value}</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="EditInfomation">Your Account
									</a>
									<c:if test="${not empty UserRoleCookie.value }">
										<a class="dropdown-item" href="AdminManager">Admin Manager</a>
									</c:if>

									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="Logout">Đăng xuất</a>
								</div></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</div>


		<div style="text-align: center;">
			<div class="row" style="left: 0">
				<div class="col-2"
					style="height: 1000px; background-color: lightgray">

					<div class="list-group" id="list-tab" role="tablist"
						style="padding-top: 140px;">
						<h2>Admin Manager</h2>
						<a class="list-group-item list-group-item-action active"
							id="list-home-list" data-toggle="list" href="#list-home"
							role="tab" aria-controls="home">Quản lý danh mục</a> <a
							class="list-group-item list-group-item-action"
							id="list-profile-list" data-toggle="list" href="#list-profile"
							role="tab" aria-controls="profile"
							href="${pageContext.request.contextPath}/AdminManager/a">Quản
							lý sản phẩm</a> <a class="list-group-item list-group-item-action"
							id="list-messages-list" data-toggle="list" href="#list-messages"
							role="tab" aria-controls="messages">Danh sách tài khoản</a>
					</div>
				</div>
				<div class="col-10">
					<div class="tab-content" id="nav-tabContent"
						style="padding-top: 150px;">

						<br />
						<div class="tab-pane fade show active" id="list-home"
							role="tabpanel" aria-labelledby="list-home-list">
							<div>
								<p class="float-left">Quản lý danh mục sản phẩm:</p>
							</div>
							<br /> <br />
							<table class="table table-bordered"
								style="width: 80%; margin: auto;">
								<caption>Danh mục sản phẩm</caption>
								<thead class="thead-dark"">
									<tr>
										<th scope="col">#</th>
										<th scope="col">Tên danh mục</th>
										<th scope="col">Hành động</th>
									</tr>
								</thead>
								<%
									for (int i = 0; i < listProductType.size(); i++) {
									String listCategories = "Listcategory" + i;
									List<Category> listCategory = (List<Category>) request.getAttribute(listCategories);
								%>
								<tbody>
									<tr>
										<th scope="row">
											<%
												out.print(i + 1);
											%>
										</th>
										<td>
											<%
												out.print(listProductType.get(i).getProducttype());
											%>
										</td>
										<td><a type="button" class="btn btn-success"
											style="color: white" data-toggle="modal"
											data-target="#modalEdit<%out.print(i);%>"> Chi tiết</a> <a
											type="button" class="btn btn-danger" style="color: white"
											data-toggle="modal" data-target="#modalDel<%out.print(i);%>">
												Xóa</a></td>



										<div class="modal fade" id="modalDel<%out.print(i);%>"
											tabindex="-1" role="dialog"
											aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">Xóa
															danh mục</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														Bạn có chắc chắn muốn xóa danh mục
														<%
														out.print(listProductType.get(i).getProducttype());
													%>
														không?
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">Close</button>
														<a type="button" class="btn btn-danger"
															href="delTypeProd?id=<%out.print(listProductType.get(i).getId());%>">Xóa</a>
													</div>
												</div>
											</div>
										</div>



										<div class="modal fade bd-example-modal-lg"
											id="modalEdit<%out.print(i);%>" tabindex="-1" role="dialog"
											aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-lg" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">Chỉnh
															sửa danh mục</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<form:form action="EditTypeProd" method="post"
															modelAttribute="ProductType">
															<div class="form-group row">
																<label for="example-text-input"
																	class="col-3 col-form-label">Tên danh mục:</label>
																<div class="col-9">
																	<form:input type="text" class="form-control"
																		id="example-text-input" aria-describedby="emailHelp"
																		value="<%=listProductType.get(i).getProducttype()%>"
																		path="producttype" required="required"
																		style="display:inline-block; width:70%; float:left" />
																	<form:input type="text" path="id"
																		value="<%=listProductType.get(i).getId()%>"
																		style="display:none;" />
																	<button type="submit" class="btn btn-success"
																		value="save">Lưu</button>
																</div>
															</div>

														</form:form>
														<hr>
														<h6 class="float-left">Danh sách danh mục con:</h6>
														<br> <br />
														<%
															for (int j = 0; j < listCategory.size(); j++) {
														%>
														<form:form action="EditCate" method="post"
															modelAttribute="Category">
															<div class="form-group row">
																<label for="example-text-input"
																	class="col-3 col-form-label" style="text-align: right;">
																	<%
																		out.print(j + 1);
																	%>
																</label>
																<div class="col-9">
																	<form:input type="text" class="form-control"
																		id="example-text-input" aria-describedby="emailHelp"
																		value="<%=listCategory.get(j).getCategoryname()%>"
																		path="categoryname" required="required"
																		style="display:inline-block; width:70%; float:left" />
																	<form:input type="text" path="idcategory"
																		value="<%=listCategory.get(j).getIdcategory()%>"
																		style="display:none;" />
																	<button type="submit" class="btn btn-success"
																		value="save">Lưu</button>
																	 <a
											type="button" class="btn btn-danger" style="color: white"
											data-toggle="modal" data-target="#modalDelType<%=i %>Cate<%=j%>">
												Xóa</a></td>



										<div class="modal fade" id="modalDelType<%=i %>Cate<%=j%>"
											tabindex="-1" role="dialog"
											aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">Xóa
															danh mục</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														Bạn có chắc chắn muốn xóa danh mục
														
														<%=listCategory.get(j).getCategoryname()%>
													
														không?
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">Close</button>
														<a type="button" class="btn btn-danger"
															href="delCate?id=<%=listCategory.get(j).getIdcategory()%>">Xóa</a>
													</div>
												</div>
											</div>
										</div>
																</div>
															</div>

														</form:form>
														<%
															}
														%>

														<a style="color: white;" class="btn btn-success"
															data-toggle="modal"
															data-target="#addCategory<%out.print(i + 1);%>">Thêm
															danh mục con</a>

														<div class="modal fade bd-example-modal-lg"
															id="addCategory<%out.print(i + 1);%>" tabindex="-1" role="dialog"
															aria-labelledby="exampleModalLabel" aria-hidden="true">
															<div class="modal-dialog modal-lg" role="document">
																<div class="modal-content">
																	<div class="modal-header">
																		<h5 class="modal-title" id="exampleModalLabel">Thêm
																			danh mục con</h5>
																		<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																	</div>
																	<div class="modal-body">
																		<form:form action="addCategory" method="post"
																			modelAttribute="Category">
																			<div class="form-group row">
																				<label for="example-text-input"
																					class="col-3 col-form-label">Tên danh mục con:
																				</label>
																				<div class="col-9">
																					<form:input type="text" class="form-control"
																						id="example-text-input"
																						aria-describedby="emailHelp" value=""
																						path="categoryname" required="required" />

																				</div>
																				<form:input type="text" path="idproducttype" value="<%=listProductType.get(i).getId()%>"
																					style="display:none" />
																			</div>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-secondary"
																			data-dismiss="modal">Close</button>
																		<button type="submit" class="btn btn-success"
																			value="add">Thêm</button>
																	</div>
																	</form:form>
																</div>
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">Close</button>
													</div>
												</div>
											</div>
										</div>
										<%
											}
										%>
									</tr>
								</tbody>
							</table>
							<a style="color: white;" class="btn btn-success"
								data-toggle="modal" data-target="#addTypeProd">Thêm danh mục</a>
							<div class="modal fade bd-example-modal-lg" id="addTypeProd"
								tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog modal-lg" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Thêm danh
												mục</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<form:form action="addTypeProd" method="post"
												modelAttribute="ProductType">
												<div class="form-group row">
													<label for="example-text-input"
														class="col-3 col-form-label">Tên danh mục: </label>
													<div class="col-9">
														<form:input type="text" class="form-control"
															id="example-text-input" aria-describedby="emailHelp"
															value="" path="producttype" required="required" />

													</div>
												</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Close</button>
											<button type="submit" class="btn btn-success" value="add">Thêm</button>
										</div>
										</form:form>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane fade" id="list-profile" role="tabpanel"
							aria-labelledby="list-profile-list">...</div>
						<div class="tab-pane fade" id="list-messages" role="tabpanel"
							aria-labelledby="list-messages-list">...</div>
					</div>
				</div>
			</div>


			<%-- 
			<h2>Customer Manager</h2>
			<form method="get" action="search">
				<input type="text" name="keyword" /> &nbsp; <input type="submit"
					value="Search" />
			</form>
			<h3>
				<a href="new">New Customer</a>
			</h3>
			<table border="1" cellpadding="5" class="table">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Name</th>
					<th scope="col">E-mail</th>
					<th scope="col">Address</th>
					<th scope="col">Action</th>
				</tr>
				<c:forEach items="${listCustomer}" var="customer">
					<tr>
						<td>${customer.id}</td>
						<td>${customer.name}</td>
						<td>${customer.email}</td>
						<td>${customer.address}</td>
						<td><a href="edit?id=${customer.id}">Edit</a>
							&nbsp;&nbsp;&nbsp; <a href="delete?id=${customer.id}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<h3>
				<a href="user">User</a>
			</h3>
			--%>
		</div>


	</div>
</body>
</html>