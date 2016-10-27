<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>LibMS</title>
</head>
<body>
	<h1>Welcome to LibMS</h1>
		
	<h3><c:out value="${name}"/></h3>
	<h3><c:out value="${currentDate}"/></h3>
	
	<c:forEach items="${books}" var="b">
	   ${b.id } ${b.title} ${b.author}
	</c:forEach>
</body>
</html>