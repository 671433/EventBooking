<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/simple.css">
<title>Søkeresultat</title>
</head>
<body>

	<h2>Resultat</h1>
	<c:if test="${mobil != null}">

   		 <p style="color: green">Innlogget som ${mobil}</p>
    
	</c:if>

	<c:forEach var="arrangement" items="${sessionScope.Arrangement}">
        <form method="post">       
                    <input type="hidden" name="arrId" value="${arrangement.id}">
                    <p>${arrangement.navn}</p>
                   
                      <p>${arrangement.adresse.poststed}</p>
                 <button type="submit" name="meldPa"> Meld på </button> 
               <c:if test="${meldtPa != null}">

   		 <p style="color: green">${meldtPa}</p>
    
	</c:if>
	</form>
            </c:forEach>
	
	
</body>
</html>