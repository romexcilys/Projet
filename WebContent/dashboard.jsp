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
		<a class="btn success" id="add" href="PageAjout">Add Computer</a>
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
				</tr>
			</thead>
			<tbody>
			
			<c:forEach items="${ computers }" var="computer">
				
				<tr> 
				
					<td> <c:out value="${computer.nom}"/> </td>
					<td>  <c:out value="${computer.introducedDate }"/> </td>
					<td>  <c:out value="${computer.discontinuedDate }"/> </td>
					<td>  <c:out value="${computer.company.nom }"/> </td>
									
				</tr>
			
			</c:forEach>

			</tbody>
		</table>
</section>

<jsp:include page="include/footer.jsp" />
