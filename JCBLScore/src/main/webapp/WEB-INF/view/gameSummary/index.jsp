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