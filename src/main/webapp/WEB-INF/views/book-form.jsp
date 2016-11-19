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
			<div class="col-xs-6">
				<form action="/libms/books" method="post" id="the-form">
				  <div class="form-group">
				    <label for="title">Title</label>
				    <input type="text" class="form-control" id="title" name="title" placeholder="Title">
            <span class="help-block">Title is not valid, please only enter 1 to 45 valid characters.</span>
				  </div>
				  <div class="form-group">
				    <label for="author">Author</label>
				    <input type="text" class="form-control" id="author" name="author" placeholder="Author">
            <span class="help-block">Title is not valid, please only enter 1 to 45 valid characters.</span>
				  </div>
				  <button type="button" id="submit-button" class="btn btn-default">Submit</button>
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
	
	<script type="text/javascript" src="/libms/resources/js/libms.js"></script>
</body>
</html>