<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>LibMS</title>
	<link rel="stylesheet" type="text/css" href="/libms/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/libms/resources/css/bootstrap-theme.min.css">
    
    <script type="text/javascript" src="/libms/resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/libms/resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/libms/resources/css/style.css">
</head>
<body>		     
     <div class="container" style="margin-top:40px;">     
       <div class="row">
         <div class="col-xs-5">
           <div class="form-group">
             <label for="country">Regex</label>
             <input type="text" id="regex">
           </div>
         </div>
         <div class="col-xs-5">
           <div class="form-group">
             <label for="headline">Pattern</label>
             <input type="text" id="pattern">
           </div>
         </div>
       </div> 
       <div class="row">
       	<div class="col-xs-10">
       		<div class="well">
       			<label id="regex-pattern-result"></label>
       		</div>
       	</div>
       </div>                   
       <div class="row">
       	<div class="col-xs-10">
       		<button class="btn btn-default btn-xs" id="regex-test">Test</button>
       	</div>
       </div>
     </div>
     
     <script src="/libms/resources/js/regex-tutorial.js"></script>   			
</body>
</html>