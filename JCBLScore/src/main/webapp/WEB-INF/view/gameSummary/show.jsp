<%@page pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<fmt:formatDate value="${game.gameDate}"pattern="yyyy年MM月dd日"/>
第${game.gameNumber}試合
<hr>
<table border=1>
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
		<td>${game.bottom5th}</td>
		<td>${game.lastRun}</td>
	</tr>

</table>
<hr>
<h2>先攻打者成績</h2>
<table border=1>
	<tr>
		<th>選手名</th>
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
			<c:choose>
				<c:when test="${i.win==1}">○</c:when>
				<c:when test="${i.lose==1}">●</c:when>
				<c:when test="${i.save==1}">S</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
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
	<c:forEach var="i" items="${lastPitchingList}">
	<tr>
		<td>
			<c:choose>
				<c:when test="${i.win==1}">○</c:when>
				<c:when test="${i.lose==1}">●</c:when>
				<c:when test="${i.save==1}">S</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
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
</body>
</html>