<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/include/header.jsp" />

<section id="main">
	<!-- AFFICHER NOMBRE EN BASE DE DONNEE OU QUI SONT RELIE A UNE COMPANY ? -->
	<h1 id="homeTitle">
		<c:out value="${ infoPage.numberComputer }" />
		Computers found
	</h1>
	<div id="actions">
		<form action="SearchComputer" method="GET">
			<input type="search" id="searchbox" name="search" value=""
				placeholder="Search name"> <input type="submit"
				id="searchsubmit" value="Filter by name" class="btn primary">
		</form>

		<div id="coincoin">
			<a class="btn success" id="addo" href="PageAjout">Add Computer</a>
		</div>
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

			<form method="post" action="PageDelete">

				<c:forEach items="${ infoPage.computers }" var="computer">
					<tr>
						<td><input type="checkbox" name="idComputer"
							value="<c:out value='${ computer.id }'/>"
							id="<c:out value='${ computer.id }'/>" /> <c:out
								value="${computer.nom}" /></td>
						<td><c:out value="${computer.introducedDate }" /></td>
						<td><c:out value="${computer.discontinuedDate }" /></td>
						<td><c:out value="${computer.companyName }" /></td>

					</tr>

				</c:forEach>
				<input type="submit" value="DELETE"
					onclick="return confirm('Are you sure ?');" class="btn primary" />
			</form>
		</tbody>
	</table>
</section>

<jsp:include page="/include/footer.jsp" />
