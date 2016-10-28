<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false"%>

<html>
<head>
<title>LibMS</title>
<link 
    rel="stylesheet" 
    type="text/css"
    href="/resources/css/style.css">
    
</head>
<body>
	<h1>Book</h1>
	<sf:form method="post" commandName="book">
	   Title: &nbsp;<sf:input path="title"/><br>
	   <sf:errors path="title" cssClass="error"/><br/>
	   Author: &nbsp;<sf:input path="author"/><br>
	   <sf:errors path="author" cssClass="error"/><br/>
	   <input type="submit">
	</sf:form>	
</body>
</html>