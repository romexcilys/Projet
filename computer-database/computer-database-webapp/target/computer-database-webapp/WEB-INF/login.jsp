<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="pag"%>

<jsp:include page="/include/header.jsp" />


<section id="main">

<div id="login-box">
 
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
	<form name='loginForm'
		action="<c:url value='j_spring_security_check' />" method='POST'>

		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value='' autocomplete="off"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		</table>

		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

	</form>
	
	</div>
</section>

<jsp:include page="/include/footer.jsp" />
