<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link rel="stylesheet" href="<c:url value="/user_interface/view/css/foundation.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/user_interface/view/css/estilo.css"/>" />
		
		<script type="text/javascript" src="<c:url value="/user_interface/view/js/lib/jquery.js"/>"></script>
		
		<title><tiles:getAsString name="title" /></title>

	</head>
	
	<body>
	
		<tiles:insertAttribute name="header"/>
	
		<tiles:insertAttribute name="content"/>
	
		<tiles:insertAttribute name="footer"/>
		
	</body>
	
</html>