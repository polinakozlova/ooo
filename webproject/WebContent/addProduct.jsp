<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Add product</title>
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
<li><a href="signUp.jsp">Sign up</a></li>
<li><a href="Controller?action=productOverview">Product overview</a></li>
</ul>
</nav>
<h2>
Add product
</h2>
</header><main>

    <form method="post" action="Controller?action=addProduct" novalidate="novalidate">
    <%	List<String> result = (List<String>) request.getAttribute("result");
		String statusMessageClass = "";
		String idClass = "has-succcess";
		String descClass = "has-succcess";
		String priceClass = "has-succcess";
		if (result != null) {
			statusMessageClass = "danger";				
			idClass = (String) request.getAttribute("idClass");
			descClass = (String) request.getAttribute("descClass");
			priceClass = (String) request.getAttribute("priceClass");
    %>
	<div class="alert alert-<%=statusMessageClass%>">
				<%	for (String message : result) {	%>
					<p><%=message%></p>
					<%}
				}
				%>
	</div>
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="id">Id</label>
        	<input type="text" id="id" name="id" required> </p>
        <p><label for="description">Description</label>
        	<input type="text" id="description" name="description" required> </p>
        <p><label for="price">Price</label>
        	<input type="text" id="price" name="price" required></p>
        <p><input type="submit" id="addProduct" value="Add product"></p>
        
    </form>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>


