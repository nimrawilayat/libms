<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false"%>

<html>
<head>
<title>LibMS</title>
	<link rel="stylesheet" type="text/css" href="/libms/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/libms/resources/css/bootstrap-theme.min.css">
    
    <script type="text/javascript" src="/libms/resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/libms/resources/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>Create New Book</h1>
	
	<div class="container">		
		<div class="row">
			<div class="col-xs-4">
				<form action="/libms/books" method="post">
				  <div class="form-group">
				    <label for="exampleInputEmail1">Id</label>
				    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Id">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Title</label>
				    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Title">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputFile">Author</label>
				    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Author">
				  </div>
				  <button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
	</div>
	
	<br>
	<form action="/libms/books" method="post">
		ID: &nbsp; <input type="text" name="id"><br>
		Title: &nbsp; <input type="text" name="title"><br>
		Author: &nbsp; <input type="text" name="author"><br>
		
		<input type="submit">
	</form>
</body>
</html>