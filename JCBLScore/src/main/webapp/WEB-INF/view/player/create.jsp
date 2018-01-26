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
				<c:when test="${teamList.teamId==playerList.teamId}">${playerList.name},</c:when>
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