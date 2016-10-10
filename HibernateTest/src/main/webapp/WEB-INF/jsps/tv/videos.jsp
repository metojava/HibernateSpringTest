<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Videos</title>
</head>
<body>

	<c:if test="not empty ${videos}">
		<c:out value="contains videos">asd</c:out>
	</c:if>


	<table>

		<tr>
			<th>VideoId:</th>
			<th>Video Name:</th>
		</tr>

		<c:forEach items="${videos}" var="vid">
			<tr>
				<td><c:out value="${vid.videoId}" /></td>
				<td><c:out value="${vid.name}" /></td>

			</tr>
		</c:forEach>

	</table>


</body>
</html>