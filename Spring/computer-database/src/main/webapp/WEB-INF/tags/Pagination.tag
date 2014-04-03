<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="currentPage" required="true" %> 
<%@ attribute name="search" required="true" %> 
<%@ attribute name="searchName" required="true" %> 
<%@ attribute name="numberPage" required="true" %> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<c:choose>
	<c:when test="${ currentPage > 1 }">
		<c:choose>
			<c:when test="${search == true }">
				<a
					href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage - 1 }'/>"><spring:message code="prece.text" text="Computer default" /></a>
			</c:when>
			<c:otherwise>
				<a href="affichage?page=<c:out value='${ currentPage - 1 }'/>"><spring:message code="prece.text" text="Computer default" /></a>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise><spring:message code="prece.text" text="Computer default" /></c:otherwise>
</c:choose>
/
<c:choose>
	<c:when test="${ currentPage < numberPage+1-1 }">
		<c:choose>
			<c:when test="${search == true }">
				<a
					href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage + 1 }'/>"><spring:message code="suiv.text" text="Computer default" /></a>
			</c:when>
			<c:otherwise>
				<a href="affichage?page=<c:out value='${ currentPage + 1 }'/>"><spring:message code="suiv.text" text="Computer default" /></a>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise><spring:message code="suiv.text" text="Computer default" /></c:otherwise>
</c:choose>
&nbsp;&nbsp;
Page
<c:out value="${ currentPage }" />
sur
<c:out value="${ numberPage }" />
