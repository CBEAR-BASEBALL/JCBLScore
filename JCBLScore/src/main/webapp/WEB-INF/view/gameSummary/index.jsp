<%@page pageEncoding="UTF-8"%>
<html>
<body>
<table border=0 CELLSPACING=1 CELLPADDING=5>
	<c:forEach var="gameList" items="${gameListDtos}">
		<c:choose>
			<c:when test="${!empty gameList.title }">
				<tr>
					<td bgcolor="#d3d3d3">${gameList.title}</td>
				</tr>
			</c:when>
		</c:choose>
	<tr>
		<td>
			<font size="-1"><a href="date/${gameList.gameId}">試合結果
			<fmt:formatDate value="${gameList.gameDate}" pattern="yyyy.MM.dd"/></a>
			</font>
		</td>
	</tr>
	</c:forEach>
</table>



</body>
</html>