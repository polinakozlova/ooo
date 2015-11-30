<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Delete confirmation</title>
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
			<h2>Delete person</h2>

		</header>
		<main>
		<form method="post" action="Controller?action=deletePersonConf" novalidate="novalidate">
	      <input type="hidden" name="userId" value="${person.userId}">
	      <p>Are you sure you want to delete person ${person.userId} ?</p>
	        <p><input type="submit" id="deletePersonConf" value="Delete person"></p>
	        
	    	</form>
		
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>