<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
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
						<c:forEach var="tprDtos" items="${tprDtos}">
						<c:choose>
							<c:when test="${empty tprDtos.gameDate && empty tprDtos.gameNumber && empty tprDtos.leagueId && empty tprDtos.playerId}">
								<tr>
									<td><b>チーム通算</b></td>
									<td>&nbsp;</td>
									<td><b><fmt:formatNumber value="${tprDtos.inning}" pattern="#0.##" /></b></td>
									<td><b>${tprDtos.hit}</b></td>
									<td><b>${tprDtos.strikeOut}</b></td>
									<td><b>${tprDtos.fourBall}</b></td>
									<td><b>${tprDtos.runs}</b></td>
									<td><b>${tprDtos.complete}</b></td>
									<td><b>${tprDtos.shutout}</b></td>
									<td><b>${tprDtos.win}</b></td>
									<td><b>${tprDtos.lose}</b></td>
									<td><b>${tprDtos.save}</b></td>
									<td><b><fmt:formatNumber value="${tprDtos.era}" pattern="#0.00" /></b></td>
								</tr>
							</c:when>
							<c:when test="${empty tprDtos.gameDate && empty tprDtos.gameNumber && empty tprDtos.leagueId}">
								<tr>
									<td><b>${tprDtos.name}(年間)</b></td>
									<td>&nbsp;</td>
									<td><b><fmt:formatNumber value="${tprDtos.inning}" pattern="#0.##" /></b></td>
									<td><b>${tprDtos.hit}</b></td>
									<td><b>${tprDtos.strikeOut}</b></td>
									<td><b>${tprDtos.fourBall}</b></td>
									<td><b>${tprDtos.runs}</b></td>
									<td><b>${tprDtos.complete}</b></td>
									<td><b>${tprDtos.shutout}</b></td>
									<td><b>${tprDtos.win}</b></td>
									<td><b>${tprDtos.lose}</b></td>
									<td><b>${tprDtos.save}</b></td>
									<td><b><fmt:formatNumber value="${tprDtos.era}" pattern="#0.00" /></b></td>
								</tr>
							</c:when>
							<c:when test="${empty tprDtos.gameDate && empty tprDtos.gameNumber}">
								<tr>
									<td><b>${tprDtos.name}(${tprDtos.title})</b></td>
									<td>&nbsp;</td>
									<td><b><fmt:formatNumber value="${tprDtos.inning}" pattern="#0.##" /></b></td>
									<td><b>${tprDtos.hit}</b></td>
									<td><b>${tprDtos.strikeOut}</b></td>
									<td><b>${tprDtos.fourBall}</b></td>
									<td><b>${tprDtos.runs}</b></td>
									<td><b>${tprDtos.complete}</b></td>
									<td><b>${tprDtos.shutout}</b></td>
									<td><b>${tprDtos.win}</b></td>
									<td><b>${tprDtos.lose}</b></td>
									<td><b>${tprDtos.save}</b></td>
									<td><b><fmt:formatNumber value="${tprDtos.era}" pattern="#0.00" /></b></td>
								</tr>
							</c:when>
							<c:when test="${empty tprDtos.gameNumber}"></c:when>
							<c:otherwise>
								<tr>
									<td>${tprDtos.name}</td>
									<td>
										<fmt:formatDate value="${tprDtos.gameDate}" pattern="MM/dd" />
										-${tprDtos.gameNumber}対${tprDtos.opponentName}</td>
									<td><fmt:formatNumber value="${tprDtos.inning}" pattern="#0.##" /></td>
									<td>${tprDtos.hit}</td>
									<td>${tprDtos.strikeOut}</td>
									<td>${tprDtos.fourBall}</td>
									<td>${tprDtos.runs}</td>
									<td>${tprDtos.complete}</td>
									<td>${tprDtos.shutout}</td>
									<td>${tprDtos.win}</td>
									<td>${tprDtos.lose}</td>
									<td>${tprDtos.save}</td>
									<td><fmt:formatNumber value="${tprDtos.era}" pattern="#0.00" /></td>
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