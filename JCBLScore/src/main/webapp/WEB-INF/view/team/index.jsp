<%@page pageEncoding="UTF-8"%>
<html>
<body>
<h1>チーム一覧</h1>
<table border=1>
	<c:forEach var="team" items="${teamList}">
	<tr>
		<td>${team.teamName}</td>
		<td><a href="./batting/${team.teamId}/${teamForm.leagueId}">打撃成績</a></td>
		<td>投球成績</td>
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
</body>
</html>