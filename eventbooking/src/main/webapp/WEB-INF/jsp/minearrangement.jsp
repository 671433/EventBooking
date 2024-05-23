<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dat109.hvl.no.eventbooking.Util.LoginUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/stilark.css">
<title>Mine Arrangement</title>
</head>
<body>
							<td>${arrangement.navn}</td>
							<td>${arrangement.adresse.poststed}</td>
							<td>${arrangement.url}</td>
							<td>${arrangement.startTid}</td>
							<td>${arrangement.sluttTid}</td>
							<td>${arrangement.pris}</td>
							<td>


								<button type="submit">Påmeldt</button>
								<p style="color: green">Du eSDASADSADr meldt på</p>



							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>

		</table>

	</div>
</body>
</html>