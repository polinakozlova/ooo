<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
<h1><span>User Management System</span></h1>
<nav>
<ul>
<li><a href="Controller">Home</a></li>
<li><a href="Controller?action=personOverview">Person overview</a></li>
<li id="actual"><a href="signUp.jsp">Sign up</a></li>
<li><a href="Controller?action=productOverview">Product overview</a></li>
</ul>
</nav>
<h2>
Sign Up
</h2>
</header><main>

    <form method="post" action="Controller?action=signUp" novalidate="novalidate">
    <%	List<String> result = (List<String>) request.getAttribute("result");
		String statusMessageClass = "";
		String firstnameClass = "has-succcess";
		String lastnameClass = "has-succcess";
		String emailClass = "has-succcess";
		String passwordClass = "has-succcess";
		if (result != null) {
			statusMessageClass = "danger";				
			firstnameClass = (String) request.getAttribute("firstnameClass");
			lastnameClass = (String) request.getAttribute("lastnameClass");
			emailClass = (String) request.getAttribute("emailClass");
			passwordClass = (String) request.getAttribute("passwordClass");
    %>
	<div class="alert alert-<%=statusMessageClass%>">
				<%	for (String message : result) {	%>
					<p><%=message%></p>
					<%}
				}
				%>
	</div>
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
         required value=""> </p>
        <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
         required value=""> </p>
        <p><label for="email">Email</label><input type="email" id="email" name="email" required value=""></p>
        <p><label for="password">Password</label><input type="password" id="password"  name="password"
         required> </p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>
        
    </form>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>


