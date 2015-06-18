<%@page pageEncoding="UTF-8"%>
<html>
<body>
<h2>スタッツ</h2>
<c:forEach var="leagueList" items="${leagueList}">
<a href="./season/${leagueList.id}">${leagueList.title}<br></a>
</c:forEach>
</body>
</html>