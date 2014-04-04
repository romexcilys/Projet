<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="pag"%>

<jsp:include page="/include/header.jsp" />

<section id="main">
	<!-- AFFICHER NOMBRE EN BASE DE DONNEE OU QUI SONT RELIE A UNE COMPANY ? -->
	<h1 id="homeTitle">
		<c:out value="${ infoPage.numberComputer }" />
		<spring:message code="nbrCompu.text" text="Computer default" />
	</h1>
	<div id="actions">
		<form action="SearchComputer" method="GET">
			<input type="hidden" name="page" value="1" /> <input type="search"
				id="searchbox" name="search" value="" placeholder="<spring:message code='search_holer.text' text='Search Name' />">
			<input type="submit" id="searchsubmit" value="<spring:message code='search.text' text='Search' />"
				class="btn primary">
		</form>

		<div id="coincoin">
			<a class="btn success" id="addo" href="PageAjout"><spring:message code='add.text' text='Add' /></a>

		</div>
	</div>

	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th id="computerName" > <spring:message code="comp.text" text="Computer" />  <pag:UrlMaker
						searchName="${ infoPage.searchName }" search="${ sessionScope.search }"
						sort="compu_name" ordre="asc" /><img src="<c:url value="/images/fleche_bas.png"  />"/></a>
					<pag:UrlMaker searchName="${ infoPage.searchName }"
						search="${ sessionScope.search }" sort="compu_name" ordre="desc" />
					<img src="<c:url value="/images/fleche_haut.png"  />" /> </a>

				</th>

				<th id="introducedDate" ><spring:message code="intro.text" text="Introduced Date" /> <pag:UrlMaker searchName="${ infoPage.searchName }"
						search="${ sessionScope.search }" sort="intro_date" ordre="asc" />
					<img src="<c:url value="/images/fleche_bas.png"  />" /></a> <pag:UrlMaker
						searchName="${ infoPage.searchName }" search="${ sessionScope.search }"
						sort="intro_date" ordre="desc" /><img src="<c:url value="/images/fleche_haut.png"  />" />
					</a>
				</th>

				<!-- Table header for Discontinued Date -->
				<th id="discontinuedDate" ><spring:message code="discon.text" text="Discontinued Date" /> <pag:UrlMaker
						searchName="${ infoPage.searchName }" search="${ sessionScope.search }"
						sort="discon_date" ordre="asc" /><img src="<c:url value="/images/fleche_bas.png"  />" /></a>
					<pag:UrlMaker searchName="${ infoPage.searchName }"
						search="${ sessionScope.search }" sort="discon_date" ordre="desc" /><img
					src="<c:url value="/images/fleche_haut.png"  />" /> </a>

				</th>
				<!-- Table header for Company -->


				<th id="companyName" ><spring:message code="compa.text" text="Company" /> <pag:UrlMaker searchName="${ infoPage.searchName }"
						search="${ sessionScope.search }" sort="compa_name" ordre="asc" /><img
					src="<c:url value="/images/fleche_bas.png"  />" /></a> <pag:UrlMaker
						searchName="${ infoPage.searchName }" search="${ sessionScope.search }"
						sort="compa_name" ordre="desc" /><img src="<c:url value="/images/fleche_haut.png"  />" />
					</a>

				</th>

				<th width="180px" id ="modification" >Modification</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${ infoPage.computers }" var="computer">

				<tr>

					<td><c:out value="${computer.name}" /></td>
					<td><c:out value="${computer.introducedDate }" /></td>
					<td><c:out value="${computer.discontinuedDate }" /></td>
					<td><c:out value="${computer.company.name }" /></td>
						<td><a class="btn primary"
						href="PageUpdate?id= <c:out value='${ computer.id }'/>"><spring:message code='update.text' text='Update' /></a>
						<a class="btn error"
						href="PageDelete?id= <c:out value='${ computer.id }'/>"
						onclick="return confirm('Are you sure ?');"><spring:message code='delete.text' text='Delete' /></a></td>
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
