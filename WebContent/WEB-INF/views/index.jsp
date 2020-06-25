<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">

<title>The Shopping Web</title>
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
<style>
.navbar{
background-color: black!important;
text-align: center;
}
.nav-item {
	padding: 0px 10px 0px 10px;
}

.nav-link {
	color: white;
	
}
.navbar a:hover{
color: orange;
}

#myVideo {
  position: fixed;
  right: 0;
  bottom: 0;
  min-width: 100%; 
  min-height: 100%;
}
</style>
<header>


	<nav class="navbar navbar-expand justify-content-center">
		<div class="nav">

			<ul class="navbar-nav mr-auto">
			<a class="nav-link navbar-brand" href="/CustomerManager/" style="color: white; padding-right: 2rem; h">Home</a>
			  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
				<li class="nav-item"><a class=" nav-link"
					href="${pageContext.request.contextPath}/Shop">Shopping Ngay</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Shop" >Giới Thiệu</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Shop">Tin Tức</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Shop">Tuyển Dụng</a></li>
				<li class="nav-item"><a class="nav-link " href="${pageContext.request.contextPath}/Shop"
					tabindex="-1" aria-disabled="true">Về chúng tôi</a></li>
			</ul>
		
		</div>
	</nav>
	

</header>
<body>

<video autoplay muted loop >
  <source  src="${pageContext.request.contextPath}/media/Pasta.mp4" type="video/mp4"/>
  Your browser does not support HTML5 video.
</video>

<iframe width="560" height="315" src="https://www.youtube.com/embed/aiwvO9u01ZY" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

</body>
</html>