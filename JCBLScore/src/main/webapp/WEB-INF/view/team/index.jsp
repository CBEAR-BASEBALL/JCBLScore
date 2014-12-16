<%@page pageEncoding="UTF-8"%>
<html>
<body>
<h1>チーム一覧</h1>
<table border=1>
	<c:forEach var="team" items="${teamList}">
	<tr>
		<td>${team.teamName}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>