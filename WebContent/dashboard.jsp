<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="include/header.jsp" />

<section id="main">
	<!-- AFFICHER NOMBRE EN BASE DE DONNEE OU QUI SONT RELIE A UNE COMPANY ? -->
	<h1 id="homeTitle"><c:out value="${ number_computer }" /> Computers found</h1>
	<div id="actions">
		<form action="SearchComputer" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="PageAjout">Add Computer</a>
	</div>

		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date</th>
					<!-- Table header for Company -->
					<th>Company</th>
				</tr>
			</thead>
			<tbody>
			
			<c:forEach items="${ computers }" var="computer">
				
				<tr> 
				
					<td> <c:out value="${computer.nom}"/> </td>
					<td>  <c:out value="${computer.introduced_date }"/> </td>
					<td>  <c:out value="${computer.discontinued_date }"/> </td>
					<td>  <c:out value="${computer.company_name }"/> </td>
									
				</tr>
			
			</c:forEach>

<!--  
				<tr>
					<td><a href="#" onclick="">ThinkPad T420</a></td>
					<td>2011-01-01</td>
					<td>2013-03-04</td>
					<td>Lenovo</td>
				</tr>
				<tr>
					<td><a href="#">Precision 3500</a></td>
					<td>2010-05-07</td>
					<td>2012-06-01</td>
					<td>Dell</td>
				</tr>
				<tr>
					<td><a href="#">Macbook Air</a></td>
					<td>2005-05-09</td>
					<td>2008-06-06</td>
					<td>Apple</td>
				</tr>
	-->
			</tbody>
		</table>
</section>

<jsp:include page="include/footer.jsp" />
