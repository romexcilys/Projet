<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="currentPage" required="true" %> 
<%@ attribute name="search" required="true" %> 
<%@ attribute name="searchName" required="true" %> 
<%@ attribute name="numberPage" required="true" %> 


<c:choose>
	<c:when test="${ currentPage > 1 }">
		<c:choose>
			<c:when test="${search == true }">
				<a
					href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage - 1 }'/>">Previous</a>
			</c:when>
			<c:otherwise>
				<a href="affichage?page=<c:out value='${ currentPage - 1 }'/>">Previous</a>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>Previous</c:otherwise>
</c:choose>
/
<c:choose>
	<c:when test="${ currentPage < numberPage }">
		<c:choose>
			<c:when test="${search == true }">
				<a
					href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage + 1 }'/>">Next</a>
			</c:when>
			<c:otherwise>
				<a href="affichage?page=<c:out value='${ currentPage + 1 }'/>">Next</a>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>Next</c:otherwise>
</c:choose>

Page
<c:out value="${ currentPage }" />
sur
<c:out value="${ numberPage }" />
