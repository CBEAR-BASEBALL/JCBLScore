<%@page pageEncoding="UTF-8"%>
<html>
<body>

<s:form>
<html:errors/>
選手名：<html:text property="name"/>
<html:select property="teamId">
		<c:forEach var="teamList" items="${teamList}" varStatus="status">
			<html:option value="${teamList.teamId}">${teamList.teamName}</html:option>
		</c:forEach>
	</html:select>
<s:submit property="createComplete" value="登録"/>
<input type="button" value="戻る" onClick="history.back()">
</s:form>
<table border=1>
	<tr>
		<th>チーム名</th>
		<th>メンバー</th>
	</tr>
<c:forEach var="teamList" items="${teamList}" >
	<tr>

		<td>${teamList.teamName}</td>
		<td>
		<c:forEach var="playerList" items="${playerList}" >
			<c:choose>
				<c:when test="${teamList.teamId==playerList.teamId}">${playerList.name}<br></c:when>
			</c:choose>
		</c:forEach>
		</td>
	</tr>
</c:forEach>
</table>
</body>
</html>