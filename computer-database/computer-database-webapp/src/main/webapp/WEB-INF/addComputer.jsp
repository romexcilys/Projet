<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/include/header.jsp" />

<section id="main">

	<!-- <h1>Add Computer</h1> -->

<div id="formulaireAdd">
	<form:form action="AjoutComputer" method="POST" modelattribute="computerDTO" commandName="computerDTO">
		<fieldset>
			<div class="clearfix">
				<label for="nameInput"><spring:message code='compu_name.text' text='Computer name' /> :</label>
				<div class="input">
					<form:input path="name" id="nameInput"></form:input>
					<form:errors path="name" cssclath="error"></form:errors>
				</div>
			</div>

			<div class="clearfix">
				<label for="introducedDateInput"><spring:message code='intro.text' text='Introduced date' /> :</label>
				<div class="input">
					<form:input id="introducedDateInput" path="introducedDate" ></form:input>
					<span class="help-inline"><spring:message code='pattern.text' text='YYYY-MM-DD' /></span><form:errors path="introducedDate" cssclath="error"></form:errors>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinuedDateInput"><spring:message code='discon.text' text='Discontinued date' /> :</label>
				<div class="input">
					<form:input  id="discontinuedDateInput" path="discontinuedDate"
						 pattern = "<spring:message code='pattern_date.pattern' text='((19|20)\d\d([-])(01|03|05|07|08|10|12)([-])(0[1-9]|[12][0-9]|3[01]))|((19|20)\d\d([-])(04|06|09|11)([-])(0[1-9]|[12][0-9]|30))|((19|20)\d\d([-])(02)([-])(0[1-9]|1[0-9]|2[0-8]))|((((18|19|20)(04|08|[2468][048]|[13579][26]))|2000)([-])02([-])29)' />"></form:input>
					<span class="help-inline"><spring:message code='pattern.text' text='YYYY-MM-DD' /></span><form:errors path="discontinuedDate" cssclath="error"></form:errors>
				</div>
			</div>
			
			<div class="clearfix">
				<label for="company"><spring:message code='compa_name.text' text='Company name' /> :</label>
				<div class="input">
					<select name="company">
						<option value="0">--</option>
						<c:forEach items="${ companys }" var="company">
							<c:choose>
								<c:when test="${ company.id == computer.companyId }">
									<option value="<c:out value='${ company.id}'/>" selected>
										<c:out value="${ company.name }" />
									</option>
								</c:when>
								<c:otherwise>
									<option value="<c:out value='${ company.id}'/>">
										<c:out value="${ company.name }" />
									</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select><span class="couleur-rouge"><c:out value="${ error.tableau.company }"/></span>
				</div>
			</div>
		</fieldset>
		<div id="barreValideAdd" >
			<input type="submit" value="<spring:message code='add.text' text='Add' />" class="btn primary"/> or <a
				href="affichage?page=1" class="btn"><spring:message code='cancel.text' text='Cancel' /></a>


		</div>
	</form:form>
	
	</div>
</section>

<jsp:include page="/include/footer.jsp" />

