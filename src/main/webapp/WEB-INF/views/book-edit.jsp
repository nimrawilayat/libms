<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
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
	<div class="container">		
		<div class="row">
			<div class="col-xs-6">
				<h1>Update Book</h1>
				<sf:form action="/libms/books" method="put" modelAttribute="book" id="the-form">
          <sf:hidden path="id"/>								  
				  <div class="form-group">
				    <label for="title" class="control-label">Title</label>
				    <sf:input path="title" placeholder="Enter Book Title" cssClass="form-control"/>
				    <sf:errors path="title" cssClass="error" />
            <span class="help-block">Title is not valid, please only enter 5 to 45 valid characters.</span>				    
				  </div>
				  <div class="form-group">
				    <label for="author" class="control-label">Author</label>
				    <sf:input path="author" placeholder="Enter Author Name" cssClass="form-control"/>
				    <sf:errors path="author" cssClass="error"/>
            <span class="help-block">Author is not valid, please only enter 5 to 45 valid characters.</span>
				  </div>
				  <button id="submit-button" type="button" class="btn btn-primary">Update Book</button>
				</sf:form>
			</div>
		</div>    
	</div>
	
	<script type="text/javascript" src="/libms/resources/js/libms.js"></script>
</body>
</html>