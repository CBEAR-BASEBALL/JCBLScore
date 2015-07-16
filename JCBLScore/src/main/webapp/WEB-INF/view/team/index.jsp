<%@page pageEncoding="UTF-8"%>
<html>
<body>
<h1>チーム一覧</h1>
<table border=1>
	<c:forEach var="team" items="${teamList}">
	<tr>
		<td>${team.teamName}</td>
		<td><a href="./batting/${team.teamId}/${teamForm.leagueId}">打撃成績</a></td>
		<td><a href="./pitching/${team.teamId}/${teamForm.leagueId}">投球成績</a></td>
	</tr>
	</c:forEach>
</table>
<br>
<s:form action="">
		<input type="button" value="戻る" onClick="history.back()">
		<s:submit property="create" value="新規チーム登録"/>
</s:form>
<s:form action="/player/">
		<s:submit property="create" value="新規メンバー登録"/>
</s:form>
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