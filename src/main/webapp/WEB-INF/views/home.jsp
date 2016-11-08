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
	
	Id: ${book.id}<br>
	Title: ${book.title}<br>
	Author: ${book.author}<br>
	
	
	<table>
		<tr><th>id</th><th>Title</th><th>Author</th></tr>
	<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.id}</td>
			<td>${book.title}</td>
			<td>${book.author}</td>
		</tr>
	</c:forEach>
	</table>


<br>
<a href="/libms/book/new">Create New Book</a>
<a href="/libms/book/new/v2">Create New Book v2</a>
	
	
</body>
</html>