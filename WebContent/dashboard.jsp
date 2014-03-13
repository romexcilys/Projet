<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="include/header.jsp" />

<section id="main">
	<!-- AFFICHER NOMBRE EN BASE DE DONNEE OU QUI SONT RELIE A UNE COMPANY ? -->
	<h1 id="homeTitle"><c:out value="${ number_computer }" /> Computers found</h1>
	<div id="actions">
		<form action="SearchComputer" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="delete" href="PageDelete">Delete Computer</a>
		<a class="btn success" id="add" href="PageAjout">Add Computer</a>
		<c:choose>
			<c:when test='${ sessionScope.choixPage == true }'><a class="btn success" id="sort" href="affichage"><c:out value="${ session.choixPage }" />Don't sort</a></c:when>
			<c:otherwise><a class="btn success" id="sort" href="affichage?page=1">Sort by pages</a></c:otherwise>
		</c:choose>
		
	</div>
	
		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date</th>
					<!-- Table header for Company -->
					<th>Company</th>
					<th>Update</th>
				</tr>
			</thead>
			<tbody>
			
			<c:forEach items="${ computers }" var="computer">
				
				<tr> 
				
					<td> <c:out value="${computer.nom}"/> </td>
					<td>  <c:out value="${computer.introducedDate }"/> </td>
					<td>  <c:out value="${computer.discontinuedDate }"/> </td>
					<td>  <c:out value="${computer.company.nom }"/> </td>
					<!--  <td><a class="btn primary" href="PageUpdate?id= <c:out value='${ computer.id }'/>" onclick="window.open('Formulaire.jsp', 'nom','height=500, width=500, top=300, left=600, toolbar=no, menubar=no, location=no, resizable=no, scrollbars=no, status=no');return false;">Update</a></td>
							 -->
							<td><a class="btn primary" href="PageUpdate?id= <c:out value='${ computer.id }'/>">Update</a>
							<a class="btn error" href="PageDelete?id= <c:out value='${ computer.id }'/>">Delete</a></td>
				</tr>
			
			</c:forEach>

			</tbody>
		</table>
</section>

<c:if test='${ sessionScope.choixPage == true }'>
	<c:choose>
		<c:when test="${ currentPage > 1 }"><a href="affichage?page=<c:out value='${ currentPage - 1 }'/>">Previous</a></c:when>
		<c:otherwise>Previous</c:otherwise>
	</c:choose>
	 / 
	 <c:choose>
		<c:when test="${ currentPage < sessionScope.numberPage }"><a href="affichage?page=<c:out value='${ currentPage + 1 }'/>">Next</a></c:when>
		<c:otherwise>Next</c:otherwise>
	</c:choose>
	Page <c:out value="${ currentPage }"/> sur <c:out value="${ sessionScope.numberPage }" />
</c:if>

<jsp:include page="include/footer.jsp" />
