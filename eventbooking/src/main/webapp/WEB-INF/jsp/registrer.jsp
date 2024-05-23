<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

			<a href="${pageContext.request.contextPath}/sokeside">
				<button class="navB">Søk</button>
			</a> <a
				href="${pageContext.request.contextPath}/sokeside/minearrangement">
				<button class="navB">Mine arrangement</button>
			</a>
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

		<form action="registrer" method="post">
			<div class=regContainer>
			<p class="tittel">Registrer ny bruker</p>
			</div>
			
				
				
				<c:if test="${feilmeldinger != null}">

   		 			<p style="color:red; font-family: verdana; font-size:20; font-weight: bold">${feilmeldinger}</p>
    
				</c:if>
				
	<div class="regInput">
				<label for="fornavn">Fornavn:</label>
				<input type="text" placeholder="Fornavn" name="fornavn" id="fornavn" required>

		
				<label for="etternavn">Etternavn:</label>
				<input type="text" placeholder="Etternavn" name="etternavn" id="etternavn" required>

				<label for="poststed">Poststed:</label>
				<input type="text" placeholder="poststed" name="poststed" id="poststed" required>

				<label for="gate">Gate:</label>
				<input type="text" placeholder="Gate" name="gate" id="gate" required>

				<label for="postnummer">Postnummer:</label>
				<input type="text" placeholder="Postnummer" name="postNummer" id="postNummer" required>

				<label for="mobil">Telefonnummer:</label>
				<input type="text" placeholder="Telefonnummer" name="mobil" id="mobil" required>

				<label for="email">Epost:</label>
				<input type="text" placeholder="Epost" name="email" id="email" required>

				<label for="passord">Passord:</label>
				<input type="password" placeholder="Passord" name="hash" id="passord" required>

				<label for="psw-repeat">Gjenta Passord:</label>
				<input type="password" placeholder="Gjenta Passord" name="salt" id="psw-repeat" required>
				
		

				
			</div>
	
				<button type="submit" class="arrangementbtn">Registrer</button>
			
		</form>

</body>
</html>