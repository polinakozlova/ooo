<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Product overview</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
		<header>
			<h1>
				<span>User Management System</span>
			</h1>
			<nav>
				<ul>
					<li><a href="Controller">Home</a></li>
					<li><a href="Controller?action=personOverview">Person overview</a></li>
					<li><a href="signUp.jsp">Sign up</a></li>
					<li  id="actual"><a href="Controller?action=productOverview">Product overview</a></li>
				</ul>
			</nav>
			<h2>Product overview</h2>

		</header>
		<main>
		
		<p><a href = "addProduct.jsp">Add product</a><p>
		<table>
			<tr>
				<th>Id</th>
				<th>Description</th>
				<th>Price</th>
				<th>Delete?</th>
			</tr>

			<c:forEach var="product" items="${products}">
				<tr>
					<td><a href="Controller?action=productInfo&id=${product.id}">${product.id}</a></td>
					<td>${product.description}</td>
					<td>${product.price}</td>
					<td><a href="Controller?action=deleteProduct&id=${product.id}">Delete product</a></td>
				</tr>
			</c:forEach>

			<caption>Products Overview</caption>

		</table>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>