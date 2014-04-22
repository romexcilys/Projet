<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="sort" required="true" %> 
<%@ attribute name="search" required="true" %> 
<%@ attribute name="searchName" required="true" %> 
<%@ attribute name="ordre" required="true" %> 


<c:choose>

	<c:when test="${ search }">
		<a href="SearchComputer?sort=<c:out value='${ sort }'/>&ordre=<c:out value='${ ordre }'/>&page=1&search=<c:out value='${ searchName }'/>">
	</c:when>
	<c:otherwise>
		<a href="affichage?sort=<c:out value='${ sort }'/>&ordre=<c:out value='${ ordre }'/>&page=1">
	</c:otherwise>

</c:choose>
