<%@page pageEncoding="UTF-8"%>
<html>
<body>
<c:forEach var="gameList" items="${gameList}">
	<br><a href="date/${gameList.gameId}">試合結果 ${gameList.gameDate}</a>
</c:forEach>
</body>
</html>