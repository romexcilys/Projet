<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/css/main.css" />" rel="stylesheet" media="screen">
</head>
<body>
	<header class="topbar">
		<h1 class="fill">
			<a href="affichage?page=1"> Application - Computer Database </a>
			Language : <a href="?lang=en">English</a>|<a href="?lang=fr">French</a>
			
			<c:choose>
				<c:when test="${ pageContext.request.userPrincipal.authenticated }">
					<a href="<c:url value="j_spring_security_logout" />" > Logout</a>
				</c:when>
			</c:choose>
			
		</h1>
		
		
	</header>