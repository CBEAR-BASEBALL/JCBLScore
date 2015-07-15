<%@page pageEncoding="UTF-8"%>
<html>
<body>
<c:forEach var="gameList" items="${gameList}">
	<br>第${gameList.gameNumber}試合
	<br><b>${gameList.team2.teamName}</b>
		<b> ${gameList.lastRun}-${gameList.firstRun}</b>
		<b> ${gameList.team.teamName}</b>
	<c:choose>
		<c:when test="${!empty loginUserDto}">
		(<a href="../edit/${gameList.gameId}">編集</a>)
	</c:when>
	</c:choose>
	<br>
	<table border=0 width="600">
		<tr>
			<td bgcolor="#ffe4e1"><b>${gameList.comment}</b></td>
		</tr>
	</table>
</c:forEach>
</body>
</html>