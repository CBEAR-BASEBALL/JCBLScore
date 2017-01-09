<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<link href="${f:url('/css/bootstrap/bootstrap.min.css') }" rel="stylesheet">
	<script src="${f:url('/js/jquery-latest.js')}" type="text/javascript"></script>
	<script src="${f:url('/js/bootstrap/bootstrap.min.js')}" type="text/javascript"></script>
</head>
<body>
<table border=1>
	<c:forEach var="leagueList" items="${leagueList}">
	<tr>
		<td><a href="./${leagueList.id}">${leagueList.title}</a></td>
	</tr>
	<c:choose>
		<c:when test="${teamForm.leagueId==leagueList.id}">
			<tr>
				<td>
					<table border=1 class="tablesorter">
						<thead>
						<tr>
							<th>名前</th>
							<th>試合</th>
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
						<c:forEach var="tbrDtos" items="${tbrDtos}">
						<c:choose>
							<c:when test="${empty tbrDtos.gameDate && empty tbrDtos.gameNumber && empty tbrDtos.leagueId && empty tbrDtos.playerId}">
								<tr>
									<td><b>チーム通算</b></td>
									<td>&nbsp;</td>
									<td><b>${tbrDtos.tpa}</b></td>
									<td><b>${tbrDtos.atBats}</b></td>
									<td><b>${tbrDtos.hit}</b></td>
									<td><b>${tbrDtos.homerun}</b></td>
									<td><b>${tbrDtos.rbi}</b></td>
									<td><b>${tbrDtos.fourBall}</b></td>
									<td><b>${tbrDtos.strikeOut}</b></td>
									<td><b>${tbrDtos.twobase}</b></td>
									<td><b><fmt:formatNumber value="${tbrDtos.average}" pattern="0.0000" /></b></td>
								</tr>
							</c:when>
							<c:when test="${empty tbrDtos.gameDate && empty tbrDtos.gameNumber && empty tbrDtos.leagueId}">
								<tr>
									<td><b>${tbrDtos.name}(年間)</b></td>
									<td>&nbsp;</td>
									<td><b>${tbrDtos.tpa}</b></td>
									<td><b>${tbrDtos.atBats}</b></td>
									<td><b>${tbrDtos.hit}</b></td>
									<td><b>${tbrDtos.homerun}</b></td>
									<td><b>${tbrDtos.rbi}</b></td>
									<td><b>${tbrDtos.fourBall}</b></td>
									<td><b>${tbrDtos.strikeOut}</b></td>
									<td><b>${tbrDtos.twobase}</b></td>
									<td><b><fmt:formatNumber value="${tbrDtos.average}" pattern="0.0000" /></b></td>
								</tr>
							</c:when>
							<c:when test="${empty tbrDtos.gameDate && empty tbrDtos.gameNumber}">
								<tr>
									<td><b>${tbrDtos.name}(${tbrDtos.title})</b></td>
									<td>&nbsp;</td>
									<td><b>${tbrDtos.tpa}</b></td>
									<td><b>${tbrDtos.atBats}</b></td>
									<td><b>${tbrDtos.hit}</b></td>
									<td><b>${tbrDtos.homerun}</b></td>
									<td><b>${tbrDtos.rbi}</b></td>
									<td><b>${tbrDtos.fourBall}</b></td>
									<td><b>${tbrDtos.strikeOut}</b></td>
									<td><b>${tbrDtos.twobase}</b></td>
									<td><b><fmt:formatNumber value="${tbrDtos.average}" pattern="0.0000" /></b></td>
								</tr>
							</c:when>
							<c:when test="${empty tbrDtos.gameNumber}"></c:when>
							<c:otherwise>
								<tr>
									<td><a href="${f:url('/player/show/') }${tbrDtos.playerId}">${tbrDtos.name}</a></td>
									<td>
										<a href="${f:url('/gameSummary/show/')}${tbrDtos.gameId}">
										<fmt:formatDate value="${tbrDtos.gameDate}" pattern="MM/dd" />
										-${tbrDtos.gameNumber}対${tbrDtos.opponentName}
										<c:choose>
										<c:when test="${tbrDtos.teamId!=tbrDtos.myteamId}">(助)</c:when>
										</c:choose>
										</a>
										</td>
									<td>${tbrDtos.tpa}</td>
									<td>${tbrDtos.atBats}</td>
									<td>${tbrDtos.hit}</td>
									<td>${tbrDtos.homerun}</td>
									<td>${tbrDtos.rbi}</td>
									<td>${tbrDtos.fourBall}</td>
									<td>${tbrDtos.strikeOut}</td>
									<td>${tbrDtos.twobase}</td>
									<td><fmt:formatNumber value="${tbrDtos.average}" pattern="0.000" /></td>
								</tr>
							</c:otherwise>
						</c:choose>

						</c:forEach>
					</table>
				</td>
			</tr>
		</c:when>
	</c:choose>
	</c:forEach>
</table>
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