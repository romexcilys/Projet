<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/include/header.jsp" />

<section id="main">
	<div id="formulaireAdd">
		<form:form action="PageUpdate" method="POST"
			modelattribute="computerDTO" commandName="computerDTO">
			<form:hidden path="id" />
			<fieldset>
				<div class="clearfix">
					<label for="nameInput"><spring:message
							code='compu_name.text' text='Computer name' /> :</label>
					<div class="input">
						<form:input path="name" id="nameInput"></form:input>
						<form:errors path="name" cssclath="error"></form:errors>
					</div>
				</div>

				<div class="clearfix">
					<label for="introducedDateInput"><spring:message
							code='intro.text' text='Introduced date' /> :</label>
					<div class="input">
						<form:input id="introducedDateInput" path="introducedDate"
							pattern="^(19|20)\d\d([-])(0[1-9]|1[012])([-])(0[1-9]|[12][0-9]|3[01])$"></form:input>
						<span class="help-inline">YYYY-MM-DD</span>
						<form:errors path="introducedDate" cssclath="error"></form:errors>
					</div>
				</div>
				<div class="clearfix">
					<label for="discontinuedDateInput"><spring:message
							code='discon.text' text='Discontinued date' /> :</label>
					<div class="input">
						<form:input id="discontinuedDateInput" path="discontinuedDate"
							data-validation="date" data-validation-format="yyyy-mm-dd"
							pattern="^(19|20)\d\d([-])(0[1-9]|1[012])([-])(0[1-9]|[12][0-9]|3[01])$"></form:input>
						<span class="help-inline">YYYY-MM-DD</span>
						<form:errors path="discontinuedDate" cssclath="error"></form:errors>
					</div>
				</div>
				<div class="clearfix">
					<label for="company"><spring:message code='compa_name.text'
							text='Company name' /> :</label>
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
						</select><span class="couleur-rouge"><c:out
								value="${ error.tableau.company }" /></span>
					</div>
				</div>
			</fieldset>
			<div id="barreValideAdd">
				<input type="submit"
					value="<spring:message code='update.text' text='Update' />"
					class="btn primary" /> or <a href="affichage?page=1" class="btn"><spring:message
						code='cancel.text' text='Cancel' /></a>


			</div>
		</form:form>
		</div>
</section>

<jsp:include page="/include/footer.jsp" />