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
	<table border=1>
		<tr>
			<th>所属チーム名</th>
			<th>所属開始</th>
			<th>所属終了</th>
			<th></th>
		</tr>
		<c:forEach var="teamList" items="${teamHistoryDtoList}">
		<tr>
			<td>${teamList.teamName}</td>
			<td>${teamList.startTitle}</td>
			<td>${teamList.endTitle}</td>
			<td><a href="../edit/${teamList.playerId}/${teamList.id}">編集</a></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>