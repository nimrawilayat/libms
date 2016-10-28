<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<h1>Welcome to LibMS</h1>
	
	<h3><c:out value="${num}"/></h3>	
	<h3><c:out value="${name}"/></h3>
	<h3><c:out value="${currentDate}"/></h3>
	
	<table>
	   <th>ID</th><th>Title</th><th>Author</th>
	   <c:forEach items="${books}" var="b">
	   <tr><td>${b.id}</td><td>${b.title}</td><td>${b.author}</td></tr>
       </c:forEach>
	</table>
	
	<a href="/book/form">Book Form</a>	
</body>
</html>