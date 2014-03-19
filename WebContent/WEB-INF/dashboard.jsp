<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/Pagination.tld" prefix="p" %>

<jsp:include page="/include/header.jsp" />

<section id="main">
	<!-- AFFICHER NOMBRE EN BASE DE DONNEE OU QUI SONT RELIE A UNE COMPANY ? -->
	<h1 id="homeTitle">
		<c:out value="${ number_computer }" />
		Computers found
	</h1>
	<div id="actions">
		<form action="SearchComputer" method="GET">
			<input type="hidden" name="page" value="1" /> <input type="search"
				id="searchbox" name="search" value="" placeholder="Search name">
			<input type="submit" id="searchsubmit" value="Filter by name"
				class="btn primary">
		</form>

		<div id="coincoin">
			<a class="btn success" id="delete" href="PageDelete">Delete
				Computer</a> <a class="btn success" id="addo" href="PageAjout">Add
				Computer</a>

		</div>
	</div>

	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th>Computer Name <c:choose>
						<c:when test="${ sessionScope.search == true}">
							<a
								href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=compu_name&ordre=asc"><img
								src="images/fleche_bas.png" /></a>
							<a
								href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=compu_name&ordre=desc"><img
								src="images/fleche_haut.png" /> </a>

						</c:when>
						<c:otherwise>
							<a
								href="affichage?page=<c:out value='${ currentPage }'/>&sort=compu_name&ordre=asc"><img
								src="images/fleche_bas.png" /></a>
							<a
								href="affichage?page=<c:out value='${ currentPage }'/>&sort=compu_name&ordre=desc"><img
								src="images/fleche_haut.png" /> </a>
						</c:otherwise>
					</c:choose>
				</th>

				<th>Introduced Date <c:choose>
						<c:when test="${ sessionScope.search == true}">
							<a
								href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=intro_date&ordre=asc"><img
								src="images/fleche_bas.png" /></a>
							<a
								href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=intro_date&ordre=desc"><img
								src="images/fleche_haut.png" /> </a>

						</c:when>
						<c:otherwise>
							<a
								href="affichage?page=<c:out value='${ currentPage }'/>&sort=intro_date&ordre=asc"><img
								src="images/fleche_bas.png" /></a>
							<a
								href="affichage?page=<c:out value='${ currentPage }'/>&sort=intro_date&ordre=desc"><img
								src="images/fleche_haut.png" /> </a>
						</c:otherwise>
					</c:choose>
				</th>

				<!-- Table header for Discontinued Date -->
				<th>Discontinued Date <c:choose>
						<c:when test="${ sessionScope.search == true}">
							<a
								href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=discon_date&ordre=asc"><img
								src="images/fleche_bas.png" /></a>
							<a
								href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=discon_date&ordre=desc"><img
								src="images/fleche_haut.png" /> </a>

						</c:when>
						<c:otherwise>
							<a
								href="affichage?page=<c:out value='${ currentPage }'/>&sort=discon_date&ordre=asc"><img
								src="images/fleche_bas.png" /></a>
							<a
								href="affichage?page=<c:out value='${ currentPage }'/>&sort=discon_date&ordre=desc"><img
								src="images/fleche_haut.png" /> </a>
						</c:otherwise>
					</c:choose>
				</th>
				<!-- Table header for Company -->


				<th>Company <c:choose>
						<c:when test="${ sessionScope.search == true}">
							<a
								href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=compa_name&ordre=asc"><img
								src="images/fleche_bas.png" /></a>
							<a
								href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=compa_name&ordre=desc"><img
								src="images/fleche_haut.png" /> </a>

						</c:when>
						<c:otherwise>
							<a
								href="affichage?page=<c:out value='${ currentPage }'/>&sort=compa_name&ordre=asc"><img
								src="images/fleche_bas.png" /></a>
							<a
								href="affichage?page=<c:out value='${ currentPage }'/>&sort=compa_name&ordre=desc"><img
								src="images/fleche_haut.png" /> </a>
						</c:otherwise>
					</c:choose>
				</th>

				<th width="180px">Modification</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${ computers }" var="computer">

				<tr>

					<td><c:out value="${computer.nom}" /></td>
					<td><c:out value="${computer.introducedDate }" /></td>
					<td><c:out value="${computer.discontinuedDate }" /></td>
					<td><c:out value="${computer.company.nom }" /></td>
					<!--  <td><a class="btn primary" href="PageUpdate?id= <c:out value='${ computer.id }'/>" onclick="window.open('Formulaire.jsp', 'nom','height=500, width=500, top=300, left=600, toolbar=no, menubar=no, location=no, resizable=no, scrollbars=no, status=no');return false;">Update</a></td>
							 -->
					<td><a class="btn primary"
						href="PageUpdate?id= <c:out value='${ computer.id }'/>">Update</a>
						<a class="btn error"
						href="PageDelete?id= <c:out value='${ computer.id }'/>"
						onclick="return confirm('Are you sure ?');">Delete</a></td>
				</tr>

			</c:forEach>

		</tbody>
	</table>
</section>

<p:pagination searchName="${ searchName }" search="${ sessionScope.search }" currentPage="${ currentPage }" numberPage="${ number_page }"/>

<jsp:include page="/include/footer.jsp" />
