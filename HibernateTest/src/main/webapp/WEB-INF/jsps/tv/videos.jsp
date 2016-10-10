<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Videos</title>
</head>
<body>

	<table>

		<tr>
			<th>VideoId:</th>
			<th>Video Name:</th>
		</tr>

		<c:forEach items="${videos}" var="vid">
			<c:out value="${vid.videoId}"></c:out>
			<c:out value="${vid.name}"></c:out>
		</c:forEach>

	</table>


</body>
</html>