<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://hurontg.com/libms/tlds/libmstags" prefix="libms"%>

<%@ page session="false"%>

<html>
<head>
<title>LibMS</title>	    
    <link rel="stylesheet" type="text/css" href="/libms/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/libms/resources/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="/libms/resources/css/style.css">
    
    <script type="text/javascript" src="/libms/resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/libms/resources/js/bootstrap.min.js"></script>    
</head>
<body>
	<h1>Books</h1>	
	
	<table class="book-table">
		<tr><th>id</th><th>Title</th><th>Author</th><th></th><th></th></tr>
	<c:forEach items="${books}" var="book">
		<tr data-book-id="${book.id}">
			<td>${book.id}</td>
			<td>${book.title}</td>
			<td>${book.author}</td>
      <td><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></td>
      <td><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></td>
		</tr>
	</c:forEach>
	</table>


<br>
<div><a href="/libms/book/new">Create New Book</a></div>
<div><a href="/libms/book/new/v2">Create New Book v2</a></div>

<script type="text/javascript" src="/libms/resources/js/libms.js"></script>	
</body>
</html>