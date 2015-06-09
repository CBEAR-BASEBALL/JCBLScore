<%@page pageEncoding="UTF-8"%>
<html>
<body>
<h1>チーム一覧</h1>
<table border=1>
	<c:forEach var="team" items="${teamList}">
	<tr>
		<td>${team.teamName}</td>
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