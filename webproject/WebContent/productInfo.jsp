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
					<li><a href="Controller?action=productOverview">Product overview</a></li>
				</ul>
			</nav>
			<h2>Update product information</h2>

		</header>
		<main>
		
		<table>
			<p>Old information:</p>
			<tr>
				<td>${product.id}</td>
				<td>${product.description}</td>
				<td>${product.price}</td>
			</tr>

			<caption>Products Overview</caption>
		</table>
		
		<form method="post" action="Controller?action=updateProductInfo" novalidate="novalidate">
	      <input type="hidden" name="id" value="${product.id}">
	        <p><label for="description">Description</label>
	        	<input type="text" id="description" name="description" required> </p>
	        <p><label for="price">Price</label>
	        	<input type="text" id="price" name="price" required></p>
	        <p><input type="submit" id="updateProductInfo" value="Update"></p>
	        
	    	</form>
		
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>