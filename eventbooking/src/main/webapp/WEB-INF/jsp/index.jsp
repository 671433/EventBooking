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
			<a href="${pageContext.request.contextPath}/arrangement">
				<button class="navB" type="submit">Registrer</button>
			</a>

			<a href="${pageContext.request.contextPath}/sokeside">
				<button class="navB" type="submit">Søk</button>
			</a>
			
			<a href="${pageContext.request.contextPath}/sokeside/minearrangement">
				<button class="navB" type="submit">Mine arrangement</button>
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
	<div class="startTittel">
	<h1>Velkommen til Eventbooking!</h1>
	

	<h2>Opplev og delta på spennende arrangementer eller lag dine egne med Eventbooking.no!</h2>
	<h3> Enten du leter etter konserter, workshops, idrettsarrangementer eller
	 fellesskapsmøter, har Eventbooking.no det du trenger.</h3>
	 <h4>Enten du er en eventyrlysten deltaker eller en ambisiøs arrangør, er Eventbooking.no din pålitelige partner på reisen mot å skape og nyte livets beste øyeblikk.
	  </h4><h4>Utforsk vårt utvalg av arrangementer i dag og la eventyret begynne!</h4>
  </div>

</body>
</html>