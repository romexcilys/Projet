<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="include/header.jsp" />

<section id="main">
	<!-- AFFICHER NOMBRE EN BASE DE DONNEE OU QUI SONT RELIE A UNE COMPANY ? -->
	<h1 id="homeTitle"><c:out value="${ number_computer }" /> Computers found</h1>
	<div id="actions">
		<form action="SearchComputer" method="GET">
		<c:if test="${ sessionScope.choixPage == true }">
			<input type="hidden" name = "page" value="1" />
		</c:if>
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
		</form>
		
		<div id="coincoin">
			<a class="btn success" id="delete" href="PageDelete">Delete Computer</a>
			<a class="btn success" id="addo" href="PageAjout">Add Computer</a>
		
		
			<c:choose>
				<c:when test='${ sessionScope.choixPage == true }'>
					<c:choose>
						<c:when test="${sessionScope.search == true }">
							<a class="btn success" id="sort" href="SearchComputer?search=<c:out value='${ searchName }'/>">Don't sort</a>
							
						</c:when>
						<c:otherwise>
							<a class="btn success" id="sort" href="affichage">Don't sort</a>
						</c:otherwise>
					</c:choose>
					
				
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${sessionScope.search == true }">
							<a class="btn success" id="sort" href="SearchComputer?search=<c:out value='${ searchName }'/>&page=1">Sort by pages</a>
						</c:when>
						<c:otherwise>
							<a class="btn success" id="sort" href="affichage?page=1">Sort by pages</a>
						</c:otherwise>
					</c:choose>
					
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th>Computer Name
						<c:choose>
						<c:when test="${ sessionScope.search == true &&  sessionScope.choixPage == true }">
							<a href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage }'/>&sort=compu_name&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage }'/>&sort=compu_name&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:when test="${ sessionScope.search == true}">
							<a href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=compu_name&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=compu_name&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:when test="${ sessionScope.choixPage == true }">
							<a href="affichage?page=<c:out value='${ currentPage }'/>&sort=compu_name&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="affichage?page=<c:out value='${ currentPage }'/>&sort=compu_name&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:otherwise>
							<a href="affichage?sort=compu_name&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="affichage?sort=compu_name&ordre=desc"><img src="images/fleche_haut.png" /> </a>
						</c:otherwise>
					</c:choose>
					</th>
					
					<th>Introduced Date
					<c:choose>
						<c:when test="${ sessionScope.search == true &&  sessionScope.choixPage == true }">
							<a href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage  }'/>&sort=intro_date&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage }'/>&sort=intro_date&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:when test="${ sessionScope.search == true}">
							<a href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=intro_date&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=intro_date&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:when test="${ sessionScope.choixPage == true }">
							<a href="affichage?page=<c:out value='${ currentPage }'/>&sort=intro_date&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="affichage?page=<c:out value='${ currentPage }'/>&sort=intro_date&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:otherwise>
							<a href="affichage?sort=intro_date&ordre=assc"><img src="images/fleche_bas.png" /></a><a href="affichage?sort=intro_date&ordre=desc"><img src="images/fleche_haut.png" /> </a>
						</c:otherwise>
					</c:choose>
					</th>
					
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date
						<c:choose>
						<c:when test="${ sessionScope.search == true &&  sessionScope.choixPage == true }">
							<a href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage }'/>&sort=discon_date&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage }'/>&sort=discon_date&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:when test="${ sessionScope.search == true}">
							<a href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=discon_date&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=discon_date&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:when test="${ sessionScope.choixPage == true }">
							<a href="affichage?page=<c:out value='${ currentPage }'/>&sort=discon_date&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="affichage?page=<c:out value='${ currentPage }'/>&sort=discon_date&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:otherwise>
							<a href="affichage?sort=discon_date&ordre=assc"><img src="images/fleche_bas.png" /></a><a href="affichage?sort=discon_date&ordre=desc"><img src="images/fleche_haut.png" /> </a>
						</c:otherwise>
					</c:choose>
					</th>
					<!-- Table header for Company -->
					
					
					<th>Company
						<c:choose>
						<c:when test="${ sessionScope.search == true &&  sessionScope.choixPage == true }">
							<a href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage }'/>&sort=compa_name&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage }'/>&sort=compa_name&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:when test="${ sessionScope.search == true}">
							<a href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=compa_name&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="SearchComputer?search=<c:out value='${ searchName }'/>&sort=compa_name&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:when test="${ sessionScope.choixPage == true }">
							<a href="affichage?page=<c:out value='${ currentPage }'/>&sort=compa_name&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="affichage?page=<c:out value='${ currentPage }'/>&sort=compa_name&ordre=desc"><img src="images/fleche_haut.png" /> </a>
							
						</c:when>
						<c:otherwise>
							<a href="affichage?sort=compa_name&ordre=asc"><img src="images/fleche_bas.png" /></a><a href="affichage?sort=compa_name&ordre=desc"><img src="images/fleche_haut.png" /> </a>
						</c:otherwise>
					</c:choose>
					</th>
					
					<th width="180px">Modification</th>
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
							<a class="btn error" href="PageDelete?id= <c:out value='${ computer.id }'/>"  onclick="return confirm('Are you sure ?');" >Delete</a></td>
				</tr>
			
			</c:forEach>

			</tbody>
		</table>
</section>

<c:if test='${ sessionScope.choixPage == true }'>
	<c:choose>
		<c:when test="${ currentPage > 1 }">
			<c:choose>
				<c:when test="${sessionScope.search == true }">
					<a href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage - 1 }'/>">Previous</a>
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
		<c:when test="${ currentPage < sessionScope.numberPage }">
			<c:choose>
				<c:when test="${sessionScope.search == true }">
					<a href="SearchComputer?search=<c:out value='${ searchName }'/>&page=<c:out value='${ currentPage + 1 }'/>">Next</a>
				</c:when>
				<c:otherwise>
					<a href="affichage?page=<c:out value='${ currentPage + 1 }'/>">Next</a>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>Next</c:otherwise>
	</c:choose>
	
	Page <c:out value="${ currentPage }"/> sur <c:out value="${ sessionScope.numberPage }" />
</c:if>

<jsp:include page="include/footer.jsp" />
