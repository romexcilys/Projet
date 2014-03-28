<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/include/header.jsp" />

<section id="main">

	<!-- <h1>Add Computer</h1> -->

<div id="formulaireAdd">

	<form action="AjoutComputer" method="POST" >
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" value="${ computer.nom }" required /> <span
						class="help-inline">Required</span><span class="couleur-rouge"><c:out value="${ error.tableau.name }"/></span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate"
						pattern="^(19|20)\d\d([-])(0[1-9]|1[012])([-])(0[1-9]|[12][0-9]|3[01])$" value="${ computer.introducedDate }"/>
					<!--  pattern="YY-MM-dd" -->
					<span class="help-inline">YYYY-MM-DD</span><span class="couleur-rouge"><c:out value="${ error.tableau.introducedDate }"/></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" data-validation="date"
						data-validation-format="yyyy-mm-dd" pattern = "^(19|20)\d\d([-])(0[1-9]|1[012])([-])(0[1-9]|[12][0-9]|3[01])$"  value="${ computer.discontinuedDate }"/>
					<!--  pattern="YY-MM-dd" -->
					<span class="help-inline">YYYY-MM-DD</span><span class="couleur-rouge"><c:out value="${ error.tableau.discontinuedDate }"/></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
						<option value="0">--</option>

						<c:forEach items="${ companys }" var="company">
							<c:choose>
								<c:when test="${ company.id == computer.companyId }">
									<option value="<c:out value='${ company.id}'/>" selected>
										<c:out value="${ company.nom }" />
									</option>
								</c:when>
								<c:otherwise>
									<option value="<c:out value='${ company.id}'/>">
										<c:out value="${ company.nom }" />
									</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select><span class="couleur-rouge"><c:out value="${ error.tableau.company }"/></span>
				</div>
			</div>
		</fieldset>
		<div id="barreValideAdd" >
			<input type="submit" value="Add" class="btn primary"> or <a
				href="affichage?page=1" class="btn">Cancel</a>


		</div>
	</form>
	
	</div>
</section>

<jsp:include page="/include/footer.jsp" />

