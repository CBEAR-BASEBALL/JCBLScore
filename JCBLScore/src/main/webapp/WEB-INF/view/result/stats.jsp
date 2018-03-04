<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="icon" href="${f:url('/favicon.ico') }" type="image/vnd.microsoft.icon">
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<%-- jquery --%>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.blockUI/2.70/jquery.blockUI.min.js"></script>
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
	<%-- <link rel="stylesheet" href="https://unpkg.com/onsenui/css/onsenui.css">
	<link rel="stylesheet" href="https://unpkg.com/onsenui/css/onsen-css-components.min.css">
	<script src="https://unpkg.com/onsenui/js/onsenui.min.js"></script>
	--%>
	<script>
	$(document).ready(function(){
		$(function(){
		    $.blockUI({
		      message: 'データの取得に時間がかかることがあります。しばらくお待ちください…',
		      css: {
		        border: 'none',
		        padding: '10px',
		        backgroundColor: '#333',
		        opacity: .5,
		        color: '#fff'
		      },
		      overlayCSS: {
		        backgroundColor: '#000',
		        opacity: 0.6
		      }
		    });
		    setTimeout($.unblockUI, 500);
		});
	//});
	});
	</script>
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
<%-- 	<ons-page>
	<ons-scroller style="height: 200px; width:100%">
--%>

<!--shinobi1-->
<%--
<script type="text/javascript" src="http://x6.karakasa.com/ufo/05087790l"></script>
<noscript><a href="http://x6.karakasa.com/bin/gg?05087790l" target="_blank">
<img src="http://x6.karakasa.com/bin/ll?05087790l" border="0"></a>
<br><span style="font-size:9px">
<img style="margin:0;vertical-align:text-bottom;" src="http://img.shinobi.jp/tadaima/fj.gif" width="19" height="11">
</span>
</noscript>
--%>
<!--shinobi2-->
<div id="contents">
<b>・JSP版(従来)</b>
<a href="${f:url('/v2/#/result/season/')}${league.id}" target=_blank>・AngularJS版</a>
<a href="${f:url('/result/ajax/')}${league.id}" target=_blank>・Ajax版</a>
<table border=1>
<tr>
	<td bgcolor="#006400"><font size="+2" COLOR="#EEEEEE">${league.title} チーム成績</font></td>
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
		<th>打率</th>
		<th>防御率</th>
		<c:forEach var="resultList" items="${resultList}" begin="0" end="${length}">
		<th>対${resultList.shortName}</th>
		</c:forEach>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="resultList" items="${resultList}" varStatus="i">
	<tr>
		<th>${i.index+1}</th>
		<td><img width="15" height="15" src="${f:url('/img/') }${resultList.teamId}.jpg">${resultList.teamName}</td>
		<td>${resultList.gameCount}</td>
		<td>${resultList.win}</td>
		<td>${resultList.lose}</td>
		<td>${resultList.draw}</td>
		<td><fmt:formatNumber value="${resultList.percentage}" pattern="0.000" /></td>
		<td>${resultList.points}</td>
		<td><a href="${f:url('/team/batting/') }${resultList.teamId}/${totalLeagueId}" ><fmt:formatNumber value="${resultList.avg}" pattern="0.000" /></a></td>
		<td><a href="${f:url('/team/pitching/') }${resultList.teamId}/${totalLeagueId}" ><fmt:formatNumber value="${resultList.era}" pattern="0.00" /></a></td>
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
<a name="average"></a>
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
			<th>&nbsp;打率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="averageTop10" items="${averageTop10}">
		<tr>
			<td align="right">${averageTop10.rank}</td>
			<td><img width="15" height="15" src="${f:url('/img/') }${averageTop10.teamId}.jpg" title="${averageTop10.teamName}">${averageTop10.name}</td>
			<td align="right">${averageTop10.tpa}</td>
			<td align="right">${averageTop10.atBats}</td>
			<td align="right">${averageTop10.hit}</td>
			<td align="right">${averageTop10.homerun}</td>
			<td align="right">${averageTop10.rbi}</td>
			<td align="right">${averageTop10.fourBall}</td>
			<td align="right">${averageTop10.strikeOut}</td>
			<td align="right">${averageTop10.twobase}</td>
			<td bgcolor="#FC9898">&nbsp;<fmt:formatNumber value="${averageTop10.average}" pattern="0.0000" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<a name="homerun"></a>
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
			<th>&nbsp;打率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="homerunTop10" items="${homerunTop10}">
		<tr>
			<td align="right">${homerunTop10.rank}</td>
			<td><img width="15" height="15" src="${f:url('/img/') }${homerunTop10.teamId}.jpg" title="${homerunTop10.teamName}">${homerunTop10.name}</td>
			<td align="right">${homerunTop10.tpa}</td>
			<td align="right">${homerunTop10.atBats}</td>
			<td align="right">${homerunTop10.hit}</td>
			<td align="right" bgcolor="#FC9898">${homerunTop10.homerun}</td>
			<td align="right">${homerunTop10.rbi}</td>
			<td align="right">${homerunTop10.fourBall}</td>
			<td align="right">${homerunTop10.strikeOut}</td>
			<td align="right">${homerunTop10.twobase}</td>
			<td>&nbsp;<fmt:formatNumber value="${homerunTop10.average}" pattern="0.0000" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<a name="rbi"></a>
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
			<th>&nbsp;打率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="rbiTop10" items="${rbiTop10}">
		<tr>
			<td align="right">${rbiTop10.rank}</td>
			<td><img width="15" height="15" src="${f:url('/img/') }${rbiTop10.teamId}.jpg" title="${rbiTop10.teamName}">${rbiTop10.name}</td>
			<td align="right">${rbiTop10.tpa}</td>
			<td align="right">${rbiTop10.atBats}</td>
			<td align="right">${rbiTop10.hit}</td>
			<td align="right">${rbiTop10.homerun}</td>
			<td align="right" bgcolor="#FC9898">${rbiTop10.rbi}</td>
			<td align="right">${rbiTop10.fourBall}</td>
			<td align="right">${rbiTop10.strikeOut}</td>
			<td align="right">${rbiTop10.twobase}</td>
			<td>&nbsp;<fmt:formatNumber value="${rbiTop10.average}" pattern="0.0000" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<a name="hit"></a>
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
			<th>&nbsp;打率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="hitTop10" items="${hitTop10}">
		<tr>
			<td align="right">${hitTop10.rank}</td>
			<td><img width="15" height="15" src="${f:url('/img/') }${hitTop10.teamId}.jpg" title="${hitTop10.teamName}">${hitTop10.name}</td>
			<td align="right">${hitTop10.tpa}</td>
			<td align="right">${hitTop10.atBats}</td>
			<td align="right" bgcolor="#FC9898">${hitTop10.hit}</td>
			<td align="right">${hitTop10.homerun}</td>
			<td align="right">${hitTop10.rbi}</td>
			<td align="right">${hitTop10.fourBall}</td>
			<td align="right">${hitTop10.strikeOut}</td>
			<td align="right">${hitTop10.twobase}</td>
			<td>&nbsp;<fmt:formatNumber value="${hitTop10.average}" pattern="0.0000" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<a name="era"></a>
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
			<th>援護率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="eraTop10" items="${eraTop10}">
		<tr>
			<td align="right">${eraTop10.rank}</td>
			<td><img width="15" height="15" src="${f:url('/img/') }${eraTop10.teamId}.jpg" title="${eraTop10.teamName}">${eraTop10.name}</td>
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
			<td align="right" ><fmt:formatNumber value="${eraTop10.runSupport}" pattern="#0.00" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<a name="win"></a>
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
			<th>援護率</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="winTop10" items="${winTop10}">
		<tr>
			<td align="right">${winTop10.rank}</td>
			<td><img width="15" height="15" src="${f:url('/img/') }${winTop10.teamId}.jpg" title="${winTop10.teamName}">${winTop10.name}</td>
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
			<td align="right"><fmt:formatNumber value="${winTop10.runSupport}" pattern="#0.00" /></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</td>
</tr>
</table>
<a name="save"></a>
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
			<td><img width="15" height="15" src="${f:url('/img/') }${saveTop10.teamId}.jpg" title="${saveTop10.teamName}">${saveTop10.name}</td>
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
<a name="strikeout"></a>
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
			<td><img width="15" height="15" src="${f:url('/img/') }${strikeOutTop10.teamId}.jpg" title="${strikeOutTop10.teamName}">${strikeOutTop10.name}</td>
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
					<td bgcolor="#006400"><font color="#EEEEEE">本塁打率<br>(AB/HR)</font></td>
					<td bgcolor="#006400"><font color="#EEEEEE">打点率<br>(RBI/AB)</font></td>
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
					<c:choose>
						<c:when test="${avgHRTop10[i].rank==1 }">
							<td bgcolor="#FC9898">${avgHRTop10[i].name}/<fmt:formatNumber value="${avgHRTop10[i].avgHomerun}" pattern="#0.00" /></td>
						</c:when>
						<c:otherwise>
							<td>${avgHRTop10[i].name}/<fmt:formatNumber value="${avgHRTop10[i].avgHomerun}" pattern="#0.00" /></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${avgRBITop10[i].rank==1 }">
							<td bgcolor="#FC9898">${avgRBITop10[i].name}/<fmt:formatNumber value="${avgRBITop10[i].avgRbi}" pattern="0.000" /></td>
						</c:when>
						<c:otherwise>
							<td>${avgRBITop10[i].name}/<fmt:formatNumber value="${avgRBITop10[i].avgRbi}" pattern="0.000" /></td>
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
		<th>RC27</th>
		<th>本塁打率</th>
		<th>打点率</th>

	</tr>
	</thead>
	<tbody>
	<c:forEach var="battingResultList" items="${battingResultList}">
	<tr>
		<td><img width="13" height="13" src="${f:url('/img/') }${battingResultList.teamId}.jpg" title="${battingResultList.teamName}"><a href="${f:url('/player/show/') }${battingResultList.playerId}" class="btn btn-default btn-xs">${battingResultList.name}</a></td>
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
		<td><fmt:formatNumber value="${battingResultList.rc27}" pattern="#0.00" /></td>
		<td><fmt:formatNumber value="${battingResultList.avgHomerun}" pattern="#0.00" /></td>
		<td><fmt:formatNumber value="${battingResultList.avgRbi}" pattern="0.0000" /></td>
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
		<th>援護率</th>

	</tr>
	</thead>
	<tbody>
	<c:forEach var="pitchingResultList" items="${pitchingResultList}">
	<tr>
		<td><img width="13" height="13" src="${f:url('/img/') }${pitchingResultList.teamId}.jpg" title="${pitchingResultList.teamName}"><a href="${f:url('/player/show/') }${pitchingResultList.playerId}" class="btn btn-default btn-xs">${pitchingResultList.name}</a></td>
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
		<td><fmt:formatNumber value="${pitchingResultList.runSupport}" pattern="#0.00" /></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
<hr>
<br><a href="${f:url('/statistics/') }">統計</a>
<br><a href="http://jcbldata.fc2web.com/cbl_stats.html">スタッツTOPへ</a>
<br><a href="http://jcbldata.fc2web.com/cbl_index.html" target="_top">HOME</a>
<hr><br><br><br><br>
</div>
<%--
</ons-scroller>
<c:choose>
	<c:when test="${device!='pc'}">

<ons-bottom-toolbar>
<a href="http://jcbldata.fc2web.com/cbl_index.html" title="HOME" target="_top">
	<ons-icon icon="fa-home" size="30px" fixed-width="true"></ons-icon>
</a>
<a href="#average" title="打率">
	<span style="font-size:30px">A</span><span style="font-size:8px">vg.</span>
</a>
<a href="#homerun" title="ホームラン">
	<span style="font-size:30px">H</span><span style="font-size:8px">R</span>
</a>
<a href="#rbi" title="打点">
	<span style="font-size:30px">R</span><span style="font-size:8px">bi</span>
</a>
<a href="#hit" title="安打数">
	<span style="font-size:30px">H</span><span style="font-size:8px">it</span>
</a>
<a href="#era" title="防御率">
	<ons-icon icon="fa-shield" size="30px" fixed-width="true"></ons-icon>
</a>
<a href="#win" title="勝利数">
	<span style="font-size:30px">W</span><span style="font-size:8px">in</span>
</a>
<a href="#save" title="セーブ">
	<span style="font-size:30px">S</span><span style="font-size:8px">ave</span>
</a>
<a href="#strikeout" title="奪三振">
	<span style="font-size:30px">K</span>
</a>
<a href="${f:url('/statistics/') }" title="統計">
	<ons-icon icon="fa-bar-chart" size="30px" fixed-width="false"></ons-icon>
</a>
<a href="http://jcbldata.fc2web.com/cbl_stats.html" title="スタッツTOP">
<ons-icon icon="fa-list" size="30px" fixed-width="false" label="TOP"></ons-icon>
</a>
</ons-bottom-toolbar>

	</c:when>
</c:choose>
</ons-page>
--%>
</body>
</html>