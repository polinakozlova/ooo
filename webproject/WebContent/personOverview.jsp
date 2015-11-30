<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Person overview</title>
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
					<li id="actual"><a href="Controller?action=personOverview">Person overview</a></li>
					<li><a href="signUp.jsp">Sign up</a></li>
					<li><a href="Controller?action=productOverview">Product overview</a></li>
				</ul>
			</nav>
			<h2>Person overview</h2>

		</header>
		<main>
		<table>
			<tr>
				<th>E-mail</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Delete person</th>
			</tr>

			<c:forEach var="person" items="${persons}">
				<tr>
					<td>${person.userId}</td>
					<td>${person.firstName}</td>
					<td>${person.lastName}</td>
					<td><a href="Controller?action=deletePerson&id=${person.userId}">Delete person</a></td>
				</tr>
			</c:forEach>

			<caption>Users Overview</caption>
		</table>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>