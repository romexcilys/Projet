<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="pag"%>

<jsp:include page="/include/header.jsp" />

<section id="main">
	<!-- AFFICHER NOMBRE EN BASE DE DONNEE OU QUI SONT RELIE A UNE COMPANY ? -->
	<h1 id="homeTitle">
		<c:out value="${ infoPage.numberComputer }" />
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
				<th id="computerName" >Computer Name  <pag:UrlMaker
						searchName="${ infoPage.searchName }" search="${ sessionScope.search }"
						sort="compu_name" ordre="asc" /><img src="images/fleche_bas.png" /></a>
					<pag:UrlMaker searchName="${ infoPage.searchName }"
						search="${ sessionScope.search }" sort="compu_name" ordre="desc" />
					<img src="images/fleche_haut.png" /> </a>

				</th>

				<th id="introducedDate" >Introduced Date <pag:UrlMaker searchName="${ infoPage.searchName }"
						search="${ sessionScope.search }" sort="intro_date" ordre="asc" />
					<img src="images/fleche_bas.png" /></a> <pag:UrlMaker
						searchName="${ infoPage.searchName }" search="${ sessionScope.search }"
						sort="intro_date" ordre="desc" /><img src="images/fleche_haut.png" />
					</a>
				</th>

				<!-- Table header for Discontinued Date -->
				<th id="discontinuedDate" >Discontinued Date <pag:UrlMaker
						searchName="${ infoPage.searchName }" search="${ sessionScope.search }"
						sort="discon_date" ordre="asc" /><img src="images/fleche_bas.png" /></a>
					<pag:UrlMaker searchName="${ infoPage.searchName }"
						search="${ sessionScope.search }" sort="discon_date" ordre="desc" /><img
					src="images/fleche_haut.png" /> </a>

				</th>
				<!-- Table header for Company -->


				<th id="companyName" >Company <pag:UrlMaker searchName="${ infoPage.searchName }"
						search="${ sessionScope.search }" sort="compa_name" ordre="asc" /><img
					src="images/fleche_bas.png" /></a> <pag:UrlMaker
						searchName="${ infoPage.searchName }" search="${ sessionScope.search }"
						sort="compa_name" ordre="desc" /><img src="images/fleche_haut.png" />
					</a>

				</th>

				<th width="180px" id ="modification" >Modification</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${ infoPage.computers }" var="computer">

				<tr>

					<td><c:out value="${computer.nom}" /></td>
					<td><c:out value="${computer.introducedDate }" /></td>
					<td><c:out value="${computer.discontinuedDate }" /></td>
					<td><c:out value="${computer.companyName }" /></td>
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

<div id="paginations">
	<pag:Pagination searchName="${ infoPage.searchName }"
		search="${ sessionScope.search }" currentPage="${ infoPage.currentPage }"
		numberPage="${ infoPage.numberPage }" />
</div>

<jsp:include page="/include/footer.jsp" />
