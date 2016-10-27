<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<head>
    <title>TEMBO</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Favicon -->
    <link rel="shortcut icon" href="favicon.ico">

    <!-- Web Fonts -->
    <link rel='stylesheet' type='text/css' href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

    <!-- CSS Global Compulsory -->
    <link rel="stylesheet" href='<c:url value="/resources/assets/plugins/bootstrap/css/bootstrap.min.css"/>'>
    <link rel="stylesheet" href='<c:url value="/resources/assets/css/style.css"/>'>

    <!-- CSS Header and Footer -->
    <link rel="stylesheet" href='<c:url value="/resources/assets/css/headers/header-default.css"/>'>
    <link rel="stylesheet" href='<c:url value="/resources/assets/css/footers/footer-v1.css"/>'>

    <!-- CSS Implementing Plugins -->
    <link rel="stylesheet" href='<c:url value="/resources/assets/plugins/animate.css"/>'>
    <link rel="stylesheet" href='<c:url value="/resources/assets/plugins/line-icons/line-icons.css"/>'>
    <link rel="stylesheet" href='<c:url value="/resources/assets/plugins/font-awesome/css/font-awesome.min.css"/>'>
    
    <!-- CSS Customization -->
    <link rel="stylesheet" href='<c:url value="/resources/assets/css/custom.css"/>'>

    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/assets/css/datatables.min.css" />' />
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/humane/libnotify.css" />' />    

<title><tiles:insertAttribute name="title" ignore="true" /></title>
<!-- JS Global Compulsory -->
<script type="text/javascript" src='<c:url value="/resources/assets/plugins/jquery/jquery.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/assets/plugins/jquery/jquery-migrate.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/assets/plugins/bootstrap/js/bootstrap.min.js"/>'></script>
<!-- JS Implementing Plugins -->
<script type="text/javascript" src='<c:url value="/resources/assets/plugins/back-to-top.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/assets/plugins/smoothScroll.js"/>'></script>
<!-- JS Customization -->
<script type="text/javascript" src='<c:url value="/resources/assets/js/custom.js"/>'></script>
<!-- JS Page Level -->
<script type="text/javascript" src='<c:url value="/resources/assets/js/app.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/assets/js/datatables.min.js" />'></script>  
<script type="text/javascript" src='<c:url value="/resources/js/jquery.form.js" />'></script>  
<script type="text/javascript" src='<c:url value="/resources/humane/humane.min.js" />'></script>  
<script type="text/javascript" src='<c:url value="/resources/js/mars/common.js" />'></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        App.init();
    });
</script>

<!--[if lt IE 9]>
    <script src="assets/plugins/respond.js"></script>
    <script src="assets/plugins/html5shiv.js"></script>
    <script src="assets/plugins/placeholder-IE-fixes.js"></script>
<![endif]-->

</head>
<body>        
   <div class="header">
      <tiles:insertAttribute name="header" />
   </div>  
   <div id="main-content-container" class="container content minhite480">
     <tiles:insertAttribute name="body" />
   </div>
   <div class="tiles_footer">
     <tiles:insertAttribute name="footer" />
   </div>      
</body>
</html>