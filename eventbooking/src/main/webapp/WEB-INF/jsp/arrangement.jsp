<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dat109.hvl.no.eventbooking.Util.LoginUtil" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/stilark.css">
<title>Arrangement Registrering</title>
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
			<form action="logout" method="post">
				<button  type="submit"class="navB">Logg ut</button>
			</form>
			<% } else { %>
			<a href="${pageContext.request.contextPath}/login"><button class="navB">Logg På</button></a>
			<% } %>
		</div>
			
	</nav>

<c:if test="${velykketReg != null}">

		<p style="color:green; font-family: verdana; font-size:20; font-weight: bold">Vellykket registrering av ${velykketReg}</p>

	</c:if>
	<c:if test="${feilmelding != null}">

		<p style="color:red; font-family: verdana; font-size:20; font-weight: bold">${feilmelding}</p>

	</c:if>
	<div class=regContainer>
			<p class="tittel">Oprett arrangement</p>
			</div>

	<form action="arrangement" method="post">

		
			
			
		<div class="regInput">
			<label for="atype">Type Arangement:</label> <input type="text"
				placeholder="Type" name="atype" id="atype" required> 
		
				<label
				for="navn">Tittel:</label> <input type="text"
				placeholder="Tittel" name="navn" id="navn" required> 
				
			
				<label
				for="poststed">Poststed:</label> <input type="text"
				placeholder="poststed" name="poststed" id="poststed" required>

			<label for="gate">Gate:</label> <input type="text"
				placeholder="Gate" name="gate" id="gate" required>
				
				 <label
				for="postnummer">Postnummer:</label> <input type="text"
				placeholder="Postnummer" name="postNummer" id="postNummer" required>

			<label for="startTid">Start Dato:</label> <input
				type="datetime-local" placeholder="" name="startTid" id="startTid"
				required> 
			
			<label for="sluttTid">Slutt Dato:</label> <input
				type="datetime-local" placeholder="" name="sluttTid" id="sluttTid"
				required> 
			
			<label for="pris">Pris:</label> <input
				type="text" placeholder="Pris" name="pris" id="pris"> 
			
			<label
				for="bilde">Bilde:</label> <input type="file"
				placeholder="Bilde" name="bilde" id="bilde"> 
			
			<label
				for="url">Ekstern lenke:</label> <input type="url"
				placeholder="URL" name="url" id="url">

</div>
			<button type="submit" class="arrangementbtn">Opprett
				arrangement</button>
	</form>
	
</body>
</html>