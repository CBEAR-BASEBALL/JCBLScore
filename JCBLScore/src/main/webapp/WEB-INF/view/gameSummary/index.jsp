<%@page pageEncoding="UTF-8"%>
<html>
<body>
<c:forEach var="gameList" items="${gameList}">
	${gameList.gameDate}
	<br>${gameList.team.teamName} ${gameList.firstRun}-${gameList.lastRun} ${gameList.team2.teamName}
	(<a href="edit/${gameList.gameId}">編集</a>)
</c:forEach>
</body>
</html>