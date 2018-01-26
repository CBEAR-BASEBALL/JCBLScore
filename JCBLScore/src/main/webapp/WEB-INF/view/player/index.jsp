<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<link rel="stylesheet" href="${f:url('/css/jquery-ui.css') }" type="text/css" media="print, projection, screen"/>
	<link href="${f:url('/css/bootstrap/bootstrap.min.css') }" rel="stylesheet">
	<script type="text/javascript" src="${f:url('/js/jquery-latest.js') }"></script>
	<script type="text/javascript" src="${f:url('/js/jquery-ui.js') }"></script>
	<script src="${f:url('/js/bootstrap/bootstrap.min.js')}" type="text/javascript"></script>
</head>
<body>
<c:choose>
	<c:when test="${!empty loginUserDto}">
		<s:form>
			<s:submit property="create" value="新規作成"/>
		</s:form>
	</c:when>
</c:choose>

<table border=1>
	<tr>
		<th>チーム名</th>
		<th width="600">メンバー</th>
	</tr>
	<c:choose>
	<c:when test="${empty loginUserDto}">
		<c:forEach var="teamList" items="${teamList}" >
			<tr>
				<td>${teamList.teamName}</td>
				<td>
					<c:forEach var="playerList" items="${playerList}" >
			<c:choose>
				<c:when test="${teamList.teamId==playerList.teamId}">
				<a href="${f:url('./show/')}${playerList.id}"  class="btn btn-default btn-xs">${playerList.name}</a>
				</c:when>
			</c:choose>
		</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
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
	</c:otherwise>
	</c:choose>
</table>
<hr>
</body>
</html>