<%@ tag description='head onderdeel van pagina' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ attribute name='title' required='true' type='java.lang.String'%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>${title}</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href='<c:url value="/bootstrap/css/bootstrap.min.css"/>'>

<!-- Custom styles for this template -->
<link rel="stylesheet" href='<c:url value="/styles/starter-template.css"/>'>
<link rel="stylesheet" href='<c:url value="/styles/default.css"/>'>
