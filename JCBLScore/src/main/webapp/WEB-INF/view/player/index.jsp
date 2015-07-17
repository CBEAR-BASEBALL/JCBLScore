<%@page pageEncoding="UTF-8"%>
<html>
<body>
<s:form>
	<s:submit property="create" value="新規作成"/>
</s:form>
<table border=1>
	<tr>
		<th>チーム名</th>
		<th width="600">メンバー</th>
	</tr>
<c:forEach var="teamList" items="${teamList}" >
	<tr>

		<td>${teamList.teamName}</td>
		<td>
		<c:forEach var="playerList" items="${playerList}" >
			<c:choose>
				<c:when test="${teamList.teamId==playerList.teamId}">
				<a href="./update/${playerList.id}">${playerList.name}</a>,
				</c:when>
			</c:choose>
		</c:forEach>
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