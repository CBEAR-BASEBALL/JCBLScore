<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/error.css') }" type="text/css" media="print, projection, screen"/>
	<link href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
	<script src="${f:url('/js/jquery-latest.js')}" type="text/javascript"></script>
	<script src="${f:url('/js/jquery-ui.js')}" type="text/javascript"></script>
</head>
<body>
	${player.name}
	<s:form>
	<table border=1>
		<tr>
			<th>所属チーム名</th>
			<th>所属開始</th>
			<th>所属終了</th>
			<th></th>
		</tr>
		<c:forEach var="teamList" items="${teamHistoryDtoList}" varStatus="i">
		<tr>
			<td>${teamList.teamName}</td>
			<td>${teamList.startTitle}</td>
			<td>${teamList.endTitle}</td>
			<td><a href="${f:url('edit')}/${teamList.playerId}/${teamList.id}">編集</a></td>

		</tr>
		</c:forEach>
		<tr>
			<td>
				<html:select property="teamId">
					<c:forEach var="teamList" items="${teamList}">
						<html:option value="${teamList.teamId}">${teamList.teamName}</html:option>
					</c:forEach>
				</html:select>
			</td>
			<td>
				<html:select property="startLeagueId">
					<c:forEach var="leagueList" items="${leagueList}">
						<html:option value="${leagueList.id}">${leagueList.shortTitle}</html:option>
					</c:forEach>
				</html:select>
			</td>
			<td>
				<html:select property="endLeagueId">
					<html:option value=""></html:option>
					<c:forEach var="leagueList" items="${leagueList}">
						<html:option value="${leagueList.id}">${leagueList.shortTitle}</html:option>
					</c:forEach>
				</html:select>
			</td>
			<td>
				<s:submit property="createComplete">追加</s:submit>
				<html:hidden property="playerId" value="${player.id}"/>
			</td>
		</tr>
	</table>

	</s:form>
</body>
</html>