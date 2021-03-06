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
<fmt:formatDate value="${game.gameDate}" pattern="yyyy年MM月dd日"/>
第${game.gameNumber}試合
<hr>
<table border=1 width="50%">
	<tr>
		<th>チーム名</th>
		<th>1</th>
		<th>2</th>
		<th>3</th>
		<th>4</th>
		<th>5</th>
		<th>R</th>
	</tr>
	<tr>
		<td>${firstTeam.teamName}</td>
		<td>${game.top1st}</td>
		<td>${game.top2nd}</td>
		<td>${game.top3rd}</td>
		<td>${game.top4th}</td>
		<td>${game.top5th}</td>
		<td>${game.firstRun}</td>
	</tr>
	<tr>
		<td>${lastTeam.teamName}</td>
		<td>${game.bottom1st}</td>
		<td>${game.bottom2nd}</td>
		<td>${game.bottom3rd}</td>
		<td>${game.bottom4th}</td>
		<td>
			<c:choose>
				<%--サヨナラの場合 --%>
				<c:when test="${game.lastRun>game.firstRun && !empty game.bottom5th}">
					${game.bottom5th}x
				</c:when>
				<%--Xのとき --%>
				<c:when test="${game.lastRun>game.firstRun && empty game.bottom5th}">
					X
				</c:when>
				<c:otherwise>
					${game.bottom5th}
				</c:otherwise>
			</c:choose>
		</td>
		<td>${game.lastRun}</td>
	</tr>

</table>
<hr>
<h2>先攻打者成績</h2>
<table border=1>
	<tr>
		<th>選手名</th>
		<th>守備</th>
		<th>打席数</th>
		<th>打数</th>
		<th>安打</th>
		<th>本塁打</th>
		<th>打点</th>
		<th>四球</th>
		<th>三振</th>
		<th>二塁打</th>
	</tr>
	<c:forEach var="i" items="${firstBattingSumList}" >
	<tr>
		<td>${i.player.name}</td>
		<td>
			<c:choose>
				<c:when test="${i.position==1}">投</c:when>
				<c:when test="${i.position==2}">捕</c:when>
				<c:when test="${i.position==3}">一</c:when>
				<c:when test="${i.position==4}">二</c:when>
				<c:when test="${i.position==5}">外</c:when>
				<c:when test="${i.position==6}">DH</c:when>
			</c:choose>
		</td>
		<td align="right">${i.tpa}</td>
		<td align="right">${i.atBats}</td>
		<td align="right">${i.hit}</td>
		<td align="right">${i.homerun}</td>
		<td align="right">${i.rbi}</td>
		<td align="right">${i.fourBall}</td>
		<td align="right">${i.strikeOut}</td>
		<td align="right">${i.twobase}</td>
	</tr>
	</c:forEach>
</table>
<hr>
<h2>先攻投手成績</h2>
<table border=1>
	<tr>
		<th>勝敗S</th>
		<th>選手名</th>
		<th>投球回</th>
		<th>被安打</th>
		<th>奪三振</th>
		<th>与四球</th>
		<th>失点</th>
		<th>投球人数</th>
		<th>被本</th>
		<th>完投</th>
		<th>完封</th>
	</tr>
	<c:forEach var="i" items="${firstPitchingList}">
	<tr>
		<td>
			${i.win}勝${i.lose}敗${i.save}S
		</td>
		<td>${i.player.name}</td>
		<td align="right"><fmt:formatNumber value="${i.inning}" pattern="0.00"/></td>
		<td align="right">${i.hit}</td>
		<td align="right">${i.strikeOut}</td>
		<td align="right">${i.fourBall}</td>
		<td align="right">${i.runs}</td>
		<td align="right">${i.pa}</td>
		<td align="right">${i.homerun}</td>
		<td align="right">${i.complete}</td>
		<td align="right">${i.shutout}</td>
	</tr>
	</c:forEach>
</table>
<hr>
<h2>後攻打者成績</h2>
<table border=1>
	<tr>
		<th>選手名</th>
		<th>守備</th>
		<th>打席数</th>
		<th>打数</th>
		<th>安打</th>
		<th>本塁打</th>
		<th>打点</th>
		<th>四球</th>
		<th>三振</th>
		<th>二塁打</th>
	</tr>
	<c:forEach var="i" items="${lastBattingSumList}" >
	<tr>
		<td>${i.player.name}</td>
		<td>
			<c:choose>
				<c:when test="${i.position==1}">投</c:when>
				<c:when test="${i.position==2}">捕</c:when>
				<c:when test="${i.position==3}">一</c:when>
				<c:when test="${i.position==4}">二</c:when>
				<c:when test="${i.position==5}">外</c:when>
				<c:when test="${i.position==6}">DH</c:when>
			</c:choose>
		</td>
		<td align="right">${i.tpa}</td>
		<td align="right">${i.atBats}</td>
		<td align="right">${i.hit}</td>
		<td align="right">${i.homerun}</td>
		<td align="right">${i.rbi}</td>
		<td align="right">${i.fourBall}</td>
		<td align="right">${i.strikeOut}</td>
		<td align="right">${i.twobase}</td>
	</tr>
	</c:forEach>
</table>
<hr>
<h2>後攻投手成績</h2>
<table border=1>
	<tr>
		<th>勝敗S</th>
		<th>選手名</th>
		<th>投球回</th>
		<th>被安打</th>
		<th>奪三振</th>
		<th>与四球</th>
		<th>失点</th>
		<th>投球人数</th>
		<th>被本</th>
		<th>完投</th>
		<th>完封</th>
	</tr>
	<c:forEach var="i" items="${lastPitchingList}">
	<tr>
		<td>
			${i.win}勝${i.lose}敗${i.save}S
		</td>
		<td>${i.player.name}</td>
		<td align="right"><fmt:formatNumber value="${i.inning}" pattern="0.00"/></td>
		<td align="right">${i.hit}</td>
		<td align="right">${i.strikeOut}</td>
		<td align="right">${i.fourBall}</td>
		<td align="right">${i.runs}</td>
		<td align="right">${i.pa}</td>
		<td align="right">${i.homerun}</td>
		<td align="right">${i.complete}</td>
		<td align="right">${i.shutout}</td>
	</tr>
	</c:forEach>
</table>
<%--<!--shinobi1-->
<script type="text/javascript" src="http://x6.karakasa.com/ufo/05087790l"></script>
<noscript><a href="http://x6.karakasa.com/bin/gg?05087790l" target="_blank">
<img src="http://x6.karakasa.com/bin/ll?05087790l" border="0"></a>
<br><span style="font-size:9px">
<img style="margin:0;vertical-align:text-bottom;" src="http://img.shinobi.jp/tadaima/fj.gif" width="19" height="11">
</span>
</noscript>
<!--shinobi2-->--%>
</body>
</html>