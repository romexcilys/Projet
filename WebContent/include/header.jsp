<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	

<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="topbar">
		<h1 class="fill">
			<c:choose>
				<c:when test='${ sessionScope.choixPage == true }'>
					<a href="affichage?page=1"> Application - Computer Database </a>
				</c:when>
				<c:otherwise>
					<a href="affichage"> Application - Computer Database </a>
				</c:otherwise>
			</c:choose>
				
		</h1>
	</header>