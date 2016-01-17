<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<script src="${f:url('/js/jquery-latest.js')}" type="text/javascript"></script>
	<script src="${f:url('/js/jquery.tablesorter.min.js')}" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function()
		       {
		          $("#battingAll").tablesorter(
		        		   {widgets: ['zebra']}
		           );
		          $("#pitchingAll").tablesorter(
		        		   {widgets: ['zebra']}
		           );
		          $("#leagueAll").tablesorter(
		        		   {widgets: ['zebra']}
		           );
		       }
		   );

	</script>

</head>
<body>
<table border=1>
<tr>
	<td bgcolor="#006400"><font size="+2" COLOR="#EEEEEE">チーム成績</font></td>
</tr>
<tr><td>
<table border=1 id="leagueAll" class="tablesorter">
	<thead>
	<tr>
		<th></th>
		<th>チーム名</th>
		<th>試合</th>
		<th>勝利</th>
		<th>敗北</th>
		<th>引き分け</th>
		<th>勝率</th>
		<th>勝ち点</th>
		<c:forEach var="resultList" items="${resultList}" begin="0" end="${length}">
		<th>対${resultList.shortName}</th>
		</c:forEach>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="resultList" items="${resultList}" varStatus="i">
	<tr>
		<th>${i.index+1}</th>
		<td>${resultList.teamName}</td>
		<td>${resultList.gameCount}</td>
		<td>${resultList.win}</td>
		<td>${resultList.lose}</td>
		<td>${resultList.draw}</td>
		<td><fmt:formatNumber value="${resultList.percentage}" pattern="0.000" /></td>
		<td>${resultList.points}</td>
		<c:forEach var="resultList2" items="${resultList2}" begin="0" end="${length}">
			<td>
			<c:forEach var="opponentList" items="${opponentList}">
				<c:choose>
					<c:when test="${resultList.teamId==opponentList.teamId &&resultList2.teamId==opponentList.opponent}">
						${opponentList.win}-${opponentList.lose}(${opponentList.draw})
					</c:when>
				</c:choose>
			</c:forEach>
			</td>
		</c:forEach>
	</tr>
	</c:forEach>
	</tbody>
</table>
</td></tr>
</table>
<hr>

<table border=0>
<tr>
	<td bgcolor="#006400"><font size="+2" COLOR="#EEEEEE">打率TOP10(規定打席:${regAtBats}打席)</font></td>
</tr>
<tr>
	<td>
		<table border=1>
		<thead>
		<tr>
			<th></th>
			<th>選手名</th>
			<th>打席数</th>
			<th>打数</th>
			<th>安打</th>
			<th>HR</th>
			<th>打点</th>
			<th>四球</th>
			<th>三振</th>
			<th>二塁打</th>
			<th>打率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="averageTop10" items="${averageTop10}">
		<tr>
			<td align="right">${averageTop10.rank}</td>
			<td>${averageTop10.name}</td>
			<td align="right">${averageTop10.tpa}</td>
			<td align="right">${averageTop10.atBats}</td>
			<td align="right">${averageTop10.hit}</td>
			<td align="right">${averageTop10.homerun}</td>
			<td align="right">${averageTop10.rbi}</td>
			<td align="right">${averageTop10.fourBall}</td>
			<td align="right">${averageTop10.strikeOut}</td>
			<td align="right">${averageTop10.twobase}</td>
			<td bgcolor="#FC9898"><fmt:formatNumber value="${averageTop10.average}" pattern="0.0000" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>

<table border=0>
<tr>
	<td bgcolor="#006400"><font size="+2" COLOR="#EEEEEE">HR TOP10</font></td>
</tr>
<tr>
	<td>
		<table border=1>
		<thead>
		<tr>
			<th></th>
			<th>選手名</th>
			<th>打席数</th>
			<th>打数</th>
			<th>安打</th>
			<th>HR</th>
			<th>打点</th>
			<th>四球</th>
			<th>三振</th>
			<th>二塁打</th>
			<th>打率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="homerunTop10" items="${homerunTop10}">
		<tr>
			<td align="right">${homerunTop10.rank}</td>
			<td>${homerunTop10.name}</td>
			<td align="right">${homerunTop10.tpa}</td>
			<td align="right">${homerunTop10.atBats}</td>
			<td align="right">${homerunTop10.hit}</td>
			<td align="right" bgcolor="#FC9898">${homerunTop10.homerun}</td>
			<td align="right">${homerunTop10.rbi}</td>
			<td align="right">${homerunTop10.fourBall}</td>
			<td align="right">${homerunTop10.strikeOut}</td>
			<td align="right">${homerunTop10.twobase}</td>
			<td><fmt:formatNumber value="${homerunTop10.average}" pattern="0.0000" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>

<table border=0>
<tr>
	<td bgcolor="#006400"><font size="+2" COLOR="#EEEEEE">打点 TOP10</font></td>
</tr>
<tr>
	<td>
		<table border=1>
		<thead>
		<tr>
			<th></th>
			<th>選手名</th>
			<th>打席数</th>
			<th>打数</th>
			<th>安打</th>
			<th>HR</th>
			<th>打点</th>
			<th>四球</th>
			<th>三振</th>
			<th>二塁打</th>
			<th>打率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="rbiTop10" items="${rbiTop10}">
		<tr>
			<td align="right">${rbiTop10.rank}</td>
			<td>${rbiTop10.name}</td>
			<td align="right">${rbiTop10.tpa}</td>
			<td align="right">${rbiTop10.atBats}</td>
			<td align="right">${rbiTop10.hit}</td>
			<td align="right">${rbiTop10.homerun}</td>
			<td align="right" bgcolor="#FC9898">${rbiTop10.rbi}</td>
			<td align="right">${rbiTop10.fourBall}</td>
			<td align="right">${rbiTop10.strikeOut}</td>
			<td align="right">${rbiTop10.twobase}</td>
			<td><fmt:formatNumber value="${rbiTop10.average}" pattern="0.0000" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>

<table border=0>
<tr>
	<td bgcolor="#006400"><font size="+2" COLOR="#EEEEEE">安打数 TOP10</font></td>
</tr>
<tr>
	<td>
		<table border=1>
		<thead>
		<tr>
			<th></th>
			<th>選手名</th>
			<th>打席数</th>
			<th>打数</th>
			<th>安打</th>
			<th>HR</th>
			<th>打点</th>
			<th>四球</th>
			<th>三振</th>
			<th>二塁打</th>
			<th>打率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="hitTop10" items="${hitTop10}">
		<tr>
			<td align="right">${hitTop10.rank}</td>
			<td>${hitTop10.name}</td>
			<td align="right">${hitTop10.tpa}</td>
			<td align="right">${hitTop10.atBats}</td>
			<td align="right" bgcolor="#FC9898">${hitTop10.hit}</td>
			<td align="right">${hitTop10.homerun}</td>
			<td align="right">${hitTop10.rbi}</td>
			<td align="right">${hitTop10.fourBall}</td>
			<td align="right">${hitTop10.strikeOut}</td>
			<td align="right">${hitTop10.twobase}</td>
			<td><fmt:formatNumber value="${hitTop10.average}" pattern="0.0000" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<table border=0>
<tr>
	<td bgcolor="#006400"><font size="+2" COLOR="#EEEEEE">防御率 TOP10(規定投球回:${regAtPitch}回)</font></td>
</tr>
<tr>
	<td>
		<table border=1>
		<thead>
		<tr>
			<th></th>
			<th>選手名</th>
			<th>登板数</th>
			<th>投球回</th>
			<th>被安打</th>
			<th>奪三振</th>
			<th>与四球</th>
			<th>失点</th>
			<th>完投</th>
			<th>完封</th>
			<th>勝ち</th>
			<th>負け</th>
			<th>S</th>
			<th>防御率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="eraTop10" items="${eraTop10}">
		<tr>
			<td align="right">${eraTop10.rank}</td>
			<td>${eraTop10.name}</td>
			<td align="right">${eraTop10.gameCount}</td>
			<td align="right"><fmt:formatNumber value="${eraTop10.inning}" pattern="#0.##" /></td>
			<td align="right">${eraTop10.hit}</td>
			<td align="right">${eraTop10.strikeOut}</td>
			<td align="right">${eraTop10.fourBall}</td>
			<td align="right">${eraTop10.runs}</td>
			<td align="right">${eraTop10.complete}</td>
			<td align="right">${eraTop10.shutout}</td>
			<td align="right">${eraTop10.win}</td>
			<td align="right">${eraTop10.lose}</td>
			<td align="right">${eraTop10.save}</td>
			<td align="right" bgcolor="#FC9898"><fmt:formatNumber value="${eraTop10.era}" pattern="#0.00" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<table border=0>
<tr>
	<td bgcolor="#006400"><font size="+2" COLOR="#EEEEEE">勝利数 TOP10</font></td>
</tr>
<tr>
	<td>
		<table border=1>
		<thead>
		<tr>
			<th></th>
			<th>選手名</th>
			<th>登板数</th>
			<th>投球回</th>
			<th>被安打</th>
			<th>奪三振</th>
			<th>与四球</th>
			<th>失点</th>
			<th>完投</th>
			<th>完封</th>
			<th>勝ち</th>
			<th>負け</th>
			<th>S</th>
			<th>防御率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="winTop10" items="${winTop10}">
		<tr>
			<td align="right">${winTop10.rank}</td>
			<td>${winTop10.name}</td>
			<td align="right">${winTop10.gameCount}</td>
			<td align="right"><fmt:formatNumber value="${winTop10.inning}" pattern="#0.##" /></td>
			<td align="right">${winTop10.hit}</td>
			<td align="right">${winTop10.strikeOut}</td>
			<td align="right">${winTop10.fourBall}</td>
			<td align="right">${winTop10.runs}</td>
			<td align="right">${winTop10.complete}</td>
			<td align="right">${winTop10.shutout}</td>
			<td align="right" bgcolor="#FC9898">${winTop10.win}</td>
			<td align="right">${winTop10.lose}</td>
			<td align="right">${winTop10.save}</td>
			<td align="right"><fmt:formatNumber value="${winTop10.era}" pattern="#0.00" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<table border=0>
<tr>
	<td bgcolor="#006400"><font size="+2" COLOR="#EEEEEE">セーブ TOP10</font></td>
</tr>
<tr>
	<td>
		<table border=1>
		<thead>
		<tr>
			<th></th>
			<th>選手名</th>
			<th>登板数</th>
			<th>投球回</th>
			<th>被安打</th>
			<th>奪三振</th>
			<th>与四球</th>
			<th>失点</th>
			<th>完投</th>
			<th>完封</th>
			<th>勝ち</th>
			<th>負け</th>
			<th>S</th>
			<th>防御率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="saveTop10" items="${saveTop10}">
		<tr>
			<td align="right">${saveTop10.rank}</td>
			<td>${saveTop10.name}</td>
			<td align="right">${saveTop10.gameCount}</td>
			<td align="right"><fmt:formatNumber value="${saveTop10.inning}" pattern="#0.##" /></td>
			<td align="right">${saveTop10.hit}</td>
			<td align="right">${saveTop10.strikeOut}</td>
			<td align="right">${saveTop10.fourBall}</td>
			<td align="right">${saveTop10.runs}</td>
			<td align="right">${saveTop10.complete}</td>
			<td align="right">${saveTop10.shutout}</td>
			<td align="right">${saveTop10.win}</td>
			<td align="right">${saveTop10.lose}</td>
			<td align="right" bgcolor="#FC9898">${saveTop10.save}</td>
			<td align="right"><fmt:formatNumber value="${saveTop10.era}" pattern="#0.00" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<table border=0>
<tr>
	<td bgcolor="#006400"><font size="+2" COLOR="#EEEEEE">奪三振 TOP10</font></td>
</tr>
<tr>
	<td>
		<table border=1>
		<thead>
		<tr>
			<th></th>
			<th>選手名</th>
			<th>登板数</th>
			<th>投球回</th>
			<th>被安打</th>
			<th>奪三振</th>
			<th>与四球</th>
			<th>失点</th>
			<th>完投</th>
			<th>完封</th>
			<th>勝ち</th>
			<th>負け</th>
			<th>S</th>
			<th>防御率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="strikeOutTop10" items="${strikeOutTop10}">
		<tr>
			<td align="right">${strikeOutTop10.rank}</td>
			<td>${strikeOutTop10.name}</td>
			<td align="right">${strikeOutTop10.gameCount}</td>
			<td align="right"><fmt:formatNumber value="${strikeOutTop10.inning}" pattern="#0.##" /></td>
			<td align="right">${strikeOutTop10.hit}</td>
			<td align="right" bgcolor="#FC9898">${strikeOutTop10.strikeOut}</td>
			<td align="right">${strikeOutTop10.fourBall}</td>
			<td align="right">${strikeOutTop10.runs}</td>
			<td align="right">${strikeOutTop10.complete}</td>
			<td align="right">${strikeOutTop10.shutout}</td>
			<td align="right">${strikeOutTop10.win}</td>
			<td align="right">${strikeOutTop10.lose}</td>
			<td align="right">${strikeOutTop10.save}</td>
			<td align="right"><fmt:formatNumber value="${strikeOutTop10.era}" pattern="#0.00" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<table>
	<tr>
		<td bgcolor="#006400"><font size="+2" color="#EEEEEE">ノンタイトル部門</font></td>
	</tr>
	<tr>
		<td>
			<table border=1>
				<tr>
					<td bgcolor="#006400"></td>
					<td bgcolor="#006400"><font color="#EEEEEE">出塁率(OBP)</font></td>
					<td bgcolor="#006400"><font color="#EEEEEE">最多二塁打</font></td>
					<td bgcolor="#006400"><font color="#EEEEEE">長打率(SLG)</font></td>
					<td bgcolor="#006400"><font color="#EEEEEE">最多四球</font></td>
					<td bgcolor="#006400"><font color="#EEEEEE">OPS</font></td>
					<td bgcolor="#006400"><font color="#EEEEEE">三振率(AB/K)</font></td>
				</tr>
				<c:forEach var="i" begin="0" end="${listSize-1}">
				<tr>
					<td>${i+1 }</td>
					<c:choose>
						<c:when test="${obpTop10[i].rank==1}">
							<td bgcolor="#FC9898">${obpTop10[i].name}/<fmt:formatNumber value="${obpTop10[i].obp}" pattern="0.0000" /></td>
						</c:when>
						<c:otherwise>
							<td>${obpTop10[i].name}/<fmt:formatNumber value="${obpTop10[i].obp}" pattern="0.0000" /></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${twobaseTop10[i].rank==1}">
							<td bgcolor="#FC9898">${twobaseTop10[i].name}/${twobaseTop10[i].twobase}本</td>
						</c:when>
						<c:otherwise>
							<td>${twobaseTop10[i].name}/${twobaseTop10[i].twobase}本</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${slgTop10[i].rank==1 }">
							<td bgcolor="#FC9898">${slgTop10[i].name}/<fmt:formatNumber value="${slgTop10[i].slg}" pattern="0.0000" /></td>
						</c:when>
						<c:otherwise>
							<td>${slgTop10[i].name}/<fmt:formatNumber value="${slgTop10[i].slg}" pattern="0.0000" /></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fourBallTop10[i].rank==1}">
							<td bgcolor="#FC9898">${fourBallTop10[i].name}/${fourBallTop10[i].fourBall}個</td>
						</c:when>
						<c:otherwise>
							<td>${fourBallTop10[i].name}/${fourBallTop10[i].fourBall}個</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${opsTop10[i].rank==1}">
							<td bgcolor="#FC9898">${opsTop10[i].name}/<fmt:formatNumber value="${opsTop10[i].ops}" pattern="0.0000" /></td>
						</c:when>
						<c:otherwise>
							<td>${opsTop10[i].name}/<fmt:formatNumber value="${opsTop10[i].ops}" pattern="0.0000" /></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${nsoTop10[i].rank==1 }">
							<td bgcolor="#FC9898">${nsoTop10[i].name}/<fmt:formatNumber value="${nsoTop10[i].notStrikeOut}" pattern="#0.00" /></td>
						</c:when>
						<c:otherwise>
							<td>${nsoTop10[i].name}/<fmt:formatNumber value="${nsoTop10[i].notStrikeOut}" pattern="#0.00" /></td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>

<hr>
<h2>打者全成績</h2>
<table border=1 id="battingAll" class="tablesorter">
	<thead>
	<tr>
		<th>選手名</th>
		<th>打席数</th>
		<th>打数</th>
		<th>安打</th>
		<th>HR</th>
		<th>打点</th>
		<th>四球</th>
		<th>三振</th>
		<th>二塁打</th>
		<th>打率</th>
		<th>長打率</th>
		<th>OPS</th>
		<th>出塁率</th>
		<th>三振率</th>

	</tr>
	</thead>
	<tbody>
	<c:forEach var="battingResultList" items="${battingResultList}">
	<tr>
		<td><a href="${f:url('/player/show/') }${battingResultList.playerId}">${battingResultList.name}</a></td>
		<td align="right">${battingResultList.tpa}</td>
		<td align="right">${battingResultList.atBats}</td>
		<td align="right">${battingResultList.hit}</td>
		<td align="right">${battingResultList.homerun}</td>
		<td align="right">${battingResultList.rbi}</td>
		<td align="right">${battingResultList.fourBall}</td>
		<td align="right">${battingResultList.strikeOut}</td>
		<td align="right">${battingResultList.twobase}</td>
		<td><fmt:formatNumber value="${battingResultList.average}" pattern="0.0000" /></td>
		<td><fmt:formatNumber value="${battingResultList.slg}" pattern="0.0000" /></td>
		<td><fmt:formatNumber value="${battingResultList.ops}" pattern="0.0000" /></td>
		<td><fmt:formatNumber value="${battingResultList.obp}" pattern="0.0000" /></td>
		<td><fmt:formatNumber value="${battingResultList.notStrikeOut}" pattern="#0.00" /></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
<hr>
<h2>投手全成績</h2>
<table border=1 id="pitchingAll" class="tablesorter">
	<thead>
	<tr>
		<th>選手名</th>
		<th>登板数</th>
		<th>投球回</th>
		<th>被安打</th>
		<th>奪三振</th>
		<th>与四球</th>
		<th>失点</th>
		<th>完投</th>
		<th>完封</th>
		<th>被本</th>
		<th>勝ち</th>
		<th>負け</th>
		<th>Ｓ</th>
		<th>防御率</th>
		<th>WHIP</th>
		<th>奪三振率</th>

	</tr>
	</thead>
	<tbody>
	<c:forEach var="pitchingResultList" items="${pitchingResultList}">
	<tr>
		<td><a href="${f:url('/player/show/') }${pitchingResultList.playerId}">${pitchingResultList.name}</a></td>
		<td align="right">${pitchingResultList.gameCount}</td>
		<td align="right"><fmt:formatNumber value="${pitchingResultList.inning}" pattern="#0.##" /></td>
		<td align="right">${pitchingResultList.hit}</td>
		<td align="right">${pitchingResultList.strikeOut}</td>
		<td align="right">${pitchingResultList.fourBall}</td>
		<td align="right">${pitchingResultList.runs}</td>
		<td align="right">${pitchingResultList.complete}</td>
		<td align="right">${pitchingResultList.shutout}</td>
		<td align="right">${pitchingResultList.homerun}</td>
		<td align="right">${pitchingResultList.win}</td>
		<td align="right">${pitchingResultList.lose}</td>
		<td align="right">${pitchingResultList.save}</td>
		<td><fmt:formatNumber value="${pitchingResultList.era}" pattern="#0.00" /></td>
		<td><fmt:formatNumber value="${pitchingResultList.whip}" pattern="#0.00" /></td>
		<td><fmt:formatNumber value="${pitchingResultList.strikeAvg}" pattern="#0.00" /></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
<hr>
<br><a href="http://jcbldata.fc2web.com/cbl_stats.html">スタッツTOPへ</a>
<br><a href="http://jcbldata.fc2web.com/cbl_index.html" target="_top">HOME</a>
<br><br><br><br><br>
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
<!--shinobi1-->
<script type="text/javascript" src="http://x6.karakasa.com/ufo/05087790l"></script>
<noscript><a href="http://x6.karakasa.com/bin/gg?05087790l" target="_blank">
<img src="http://x6.karakasa.com/bin/ll?05087790l" border="0"></a>
<br><span style="font-size:9px">
<img style="margin:0;vertical-align:text-bottom;" src="http://img.shinobi.jp/tadaima/fj.gif" width="19" height="11">
</span>
</noscript>
<!--shinobi2-->
</body>
</html>