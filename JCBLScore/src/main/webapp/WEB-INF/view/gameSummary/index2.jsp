<%@page pageEncoding="UTF-8"%>
<html>
<body>
<c:forEach var="gameList" items="${gameList}">
	<br>第${gameList.gameNumber}試合
	<br><b>${gameList.team2.teamName}</b>
		<b> <a href="../show/${gameList.gameId}">${gameList.lastRun}-${gameList.firstRun}</a></b>
		<b> ${gameList.team.teamName}</b>
	<c:choose>
		<c:when test="${!empty loginUserDto.id}">
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
<hr>
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- 1 -->
<ins class="adsbygoogle"
     style="display:block"
     data-ad-client="ca-pub-6455792541973521"
     data-ad-slot="1903562094"
     data-ad-format="auto"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>
</body>
</html>