<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dat109.hvl.no.eventbooking.Util.LoginUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/stilark.css">
<title>Event Booking</title>
</head>
<body>

	<nav>
		<div class="nav1">
			<h2>Eventbooking</h2>
			<form action="${pageContext.request.contextPath}/arrangement">
				<button class="navB" type="submit">Registrer</button>
			</form>

			<form action="${pageContext.request.contextPath}/sokeside">
				<button class="navB">Søk</button>
			</form>
			<form action="${pageContext.request.contextPath}/sokeside/minearrangement">
				<button class="navB">Mine arrangement</button>
			</form>
		</div>
		<div class="logginnut">
		<c:if test="${navn != null}">
			<p class="innloggetNavn">Velkommen, ${navn}</p>
		</c:if>
			<% if( LoginUtil.erBrukerInnlogget(session) ) { %>
			<form action="${pageContext.request.contextPath}/logout" method="post">
				<button  type="submit"class="navB">Logg ut</button>
			</form>
			<% } else { %>
			<a href="${pageContext.request.contextPath}/login"><button class="navB">Logg På</button></a>
			<% } %>
		</div>
			
	</nav>

	<div class="boks">

		<c:if test="${valgtArrangement != null}">
			<p  style="color:green; font-family: verdana; font-size:20; font-weight: bold">Du har meldt deg på ${valgtArrangement}</p>
		</c:if>

		<p class="tittel">Søk etter arrangementer</p>

	<p style="color:red; font-family: verdana; font-size:20; font-weight: bold"> ${login}</p>
	
		<form method="post"
			action="${pageContext.request.contextPath}/sokeside/sok"
			class="sookeform">
			
					<th>Type: <select id="Type"
						name="Type" class="input-box">
							<option></option>
							<c:forEach var="type" items="${types}">
								<option value="${type}">${type}</option>
							</c:forEach>
					</select></th>
					<th>Dato: <input type="date" name="dato" class="input-box"></th>
					<th>Sted: <select name="Sted" id="Sted" class="input-box">
								<option></option>
							<c:forEach var="sted" items="${poststeder}">
								<option value="${sted}">${sted}</option>
							</c:forEach>
					</select>
					</th>
					 
        <button type="submit" class="sokButton">Søk</button>

		
		</form>
		</div>
			<c:if test="${soker != null}">
		
		
   <div class="content">
		<table class="sookres">
    <tr>
        <th>Navn</th>
        <th>Adresse</th>
        <th>URL</th>
        <th>Starttid</th>
        <th>Slutttid</th>
        <th>Pris</th>
        <th>Påmeldingsstatus</th>
    </tr>
			</c:if>
		
		

			<c:forEach var="arrangement" items="${sessionScope.Arrangement}">
				<tr>
					<td>${arrangement.navn}</td>
					<td>${arrangement.adresse.poststed}</td>
					<td>${arrangement.url}</td>
					<td>${arrangement.startTid}</td>
					<td>${arrangement.sluttTid}</td>
					<td>${arrangement.pris}</td>
					<td><c:choose>
							<c:when test="${pameldte.contains(arrangement.id)}">
									
								<p style="color: green">Du er meldt på</p>
							</c:when>
							<c:otherwise>
								<form method="post"
									action="${pageContext.request.contextPath}/sokeside/meldPa">
									<button type="submit">Meld på</button>
									<input type="hidden" name="arrId" value="${arrangement.id}">
								</form>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>

		</table>
</div>
	

</body>
</html>