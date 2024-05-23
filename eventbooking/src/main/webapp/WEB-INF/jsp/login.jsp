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
			<form action="logout" method="post">
				<button  type="submit"class="navB">Logg ut</button>
			</form>
			<% } else { %>
			<a href="${pageContext.request.contextPath}/login"><button class="navB">Logg På</button></a>
			<% } %>
		</div>
			
	</nav>
	<c:if test="${beskjed_message != null}">

   		 <p style="color:red; font-family: verdana; font-size:20; font-weight: bold">${beskjed_message}</p>
    
	</c:if>
	<div class=regContainer>
			<p class="tittel">Logg inn</p>
	</div>
	
		<form method="post">
		<div class="logInput">
		<div class="regInput">
			<label for="mobil">Mobil:</label> <input type="text" name="mobil" />
			
			<label for="passord">Passord:</label> <input type="password" name="passord" />
			</div>
			</div>
			<button type="submit" class="arrangementbtn">Logg inn</button>
	</form>

<form action="${pageContext.request.contextPath}/registrer"  >

<button  class="arrangementbtn">Ny Bruker</button>

</form>

</body>
</html>