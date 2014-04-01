<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="/include/header.jsp" />

<section id="main">

	<!-- <h1>Add Computer</h1> -->

<div id="formulaireAdd">

	<form:form action="AjoutComputer" method="POST" modelattribute="computerDTO" commandName="computerDTO">
		<fieldset>
			<div class="clearfix">
				<label for="nameInput">Computer name:</label>
				<div class="input">
					<form:input path="name" id="nameInput"></form:input>
					<form:errors path="name" cssclath="error"></form:errors>
				</div>
			</div>

			<div class="clearfix">
				<label for="introducedDateInput">Introduced date:</label>
				<div class="input">
					<form:input id="introducedDateInput" path="introducedDate"
						pattern="^(19|20)\d\d([-])(0[1-9]|1[012])([-])(0[1-9]|[12][0-9]|3[01])$" ></form:input><!-- ${ computer.introducedDate } -->
					<!--  pattern="YY-MM-dd" -->
					<span class="help-inline">YYYY-MM-DD</span><form:errors path="introducedDate" cssclath="error"></form:errors>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinuedDateInput">Discontinued date:</label>
				<div class="input">
					<form:input  id="discontinuedDateInput" path="discontinuedDate" data-validation="date"
						data-validation-format="yyyy-mm-dd" pattern = "^(19|20)\d\d([-])(0[1-9]|1[012])([-])(0[1-9]|[12][0-9]|3[01])$"></form:input><!-- ${ computer.discontinuedDate } -->
					<!--  pattern="YY-MM-dd" -->
					<span class="help-inline">YYYY-MM-DD</span><form:errors path="discontinuedDate" cssclath="error"></form:errors>
				</div>
			</div>
			<div class="clearfix">
				<label for="companyOptions">Company Name:</label>
				<div class="input">
					<form:select id="companyOptions" path="companyId">
						<form:option value="0">--</form:option>
<!-- selected="true" -->
						<c:forEach items="${ companys }" var="company">
							<c:choose>
								<c:when test="${ company.id == computer.companyId }">
									<form:option value="<c:out value='${ company.id}'/>" >
										<c:out value="${ company.name }" />
									</form:option>
								</c:when>
								<c:otherwise>
									<form:option value="<c:out value='${ company.id}'/>">
										<c:out value="${ company.name }" />
									</form:option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</form:select><form:errors path="companyId" cssclath="error"></form:errors>
				</div>
			</div>
		</fieldset>
		<div id="barreValideAdd" >
			<input type="submit" value="Add" class="btn primary"/> or <a
				href="affichage?page=1" class="btn">Cancel</a>


		</div>
	</form:form>
	
	</div>
</section>

<jsp:include page="/include/footer.jsp" />

