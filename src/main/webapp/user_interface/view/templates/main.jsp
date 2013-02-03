<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>

	<head>
	
		<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
		
		<link rel="stylesheet" href="<c:url value="/user_interface/resources/css/normalize.css"/>">
		<link rel="stylesheet" href="<c:url value="/user_interface/resources/css/talk_about.css"/>">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css">
		
		<script src="<c:url value="/user_interface/resources/js/lib/prefixfree.min.js"/>"></script>
		<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
		<script src="<c:url value="http://code.jquery.com/ui/1.10.0/jquery-ui.js"/>"></script>

		<title><tiles:getAsString name="title" /></title>

	</head>
	
	<body>
	
		<tiles:insertAttribute name="header"/>
		
		<tiles:insertAttribute name="content"/>
	
		<tiles:insertAttribute name="footer"/>
		
	</body>
	
</html>