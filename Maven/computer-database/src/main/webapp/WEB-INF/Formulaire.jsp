<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/include/header.jsp" />

<section id="main">

	<form action="PageUpdate" method="POST">
		<input type="hidden" name="idComputer"
			value="<c:out value='${ computer.id }'/>" />
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name"
						value="<c:out value='${ computer.nom }'/>" required /> <span
						class="help-inline">Required</span><span class="couleur-rouge"><c:out value="${ error.tableau.name }"/></span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate"
						value="<c:out value='${ computer.introducedDate }'/>"
						pattern="^(19|20)\d\d([- /.])(0[1-9]|1[012])\2(0[1-9]|[12][0-9]|3[01])$" />
					<!--  pattern="YY-MM-dd" -->
					<span class="help-inline">YYYY-MM-DD</span><span class="couleur-rouge"><c:out value="${ error.tableau.introducedDate }"/></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate"
						value="<c:out value='${ computer.discontinuedDate }'/>"
						pattern="^(19|20)\d\d([- /.])(0[1-9]|1[012])\2(0[1-9]|[12][0-9]|3[01])$" />
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
								<c:when test="${ computer.company.id == company.id }">
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
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Update" class="btn primary"> or <a
				href="affichage?page=1" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="/include/footer.jsp" />