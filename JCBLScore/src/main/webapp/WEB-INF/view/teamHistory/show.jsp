<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<%-- jquery --%>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	<%-- bootstrap --%>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	<%-- jquery-ui --%>
	<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" media="print, projection, screen"/>
	<%-- highcharts --%>
	<script type="text/javascript" src="https://code.highcharts.com/4.2.2/highcharts.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/2.2/highcharts-more.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.29.4/js/jquery.tablesorter.min.js" type="text/javascript"></script>
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
			<td>
				<a href="../edit/${teamList.playerId}/${teamList.id}">編集</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<s:form>
		<c:choose>
			<c:when test="${!empty loginUserDto}">
				<s:submit property="create" value="追加"/>
				<html:hidden property="playerId" value="${player.id}"/>
			</c:when>
		</c:choose>

	</s:form>
</body>
</html>