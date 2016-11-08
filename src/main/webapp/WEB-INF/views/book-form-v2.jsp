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
	<div class="container">		
		<div class="row">
			<div class="col-xs-4">
				<sf:form action="/libms/books" method="post" modelAttribute="book">
				<h1>Create New Book</h1>
				  <div class="form-group">
				    <label for="exampleInputEmail1">Id</label>
				    <sf:input path="id"/>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Title</label>
				    <sf:input path="title"/><br>
				    <sf:errors path="title" cssClass="error" />				    
				  </div>
				  <div class="form-group">
				    <label for="exampleInputFile">Author</label>
				    <sf:input path="author" placeholder="Author"/><br>
				    <sf:errors path="author" cssClass="error"/>
				  </div>
				  <button type="submit" class="btn btn-default">Submit</button>
				</sf:form>
			</div>
		</div>
	</div>
	
	
</body>
</html>