<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://hurontg.com/libms/tlds/libmstags" prefix="libms"%>

<%@ page session="false"%>

<html>
<head>
<title>LibMS</title>	    
    <link rel="stylesheet" type="text/css" href="/libms/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/libms/resources/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    
    <script type="text/javascript" src="/libms/resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/libms/resources/js/bootstrap.min.js"></script>    
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
	

<br>
	<libms:customTag1 count="3"/>
	<libms:customTag2 domainObjectName="book"/>	
</body>
</html>