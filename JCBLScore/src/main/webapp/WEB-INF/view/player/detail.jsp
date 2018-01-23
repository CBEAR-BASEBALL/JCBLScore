<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" media="print, projection, screen"/>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<script src="${f:url('/js/bootstrap/bootstrap.min.js')}" type="text/javascript"></script>
	<!--  <script type="text/javascript" src="${f:url('/js/highcharts.src.js') }"></script>
	<script type="text/javascript" src="${f:url('/js/highcharts-more.src.js') }"></script>
	-->
	<script src="${f:url('/js/jquery.tablesorter.min.js')}" type="text/javascript"></script>
	<link href="http://ajax.googleapis.com/ajax/libs/jquerymobile/1.4.5/jquery.mobile.min.css"  rel="stylesheet">
	<script src="http://ajax.googleapis.com/ajax/libs/jquerymobile/1.4.5/jquery.mobile.min.js" type="text/javascript"></script>
	<script>
	$(function() {
		$( "#tab-b" ).tabs();
	});
	$(function() {
		$( "#tab-p" ).tabs();
	});
	</script>
</head>
<body>
<hr>
<h2>選手詳細</h2>
<table border=1>
	<tr>
		<td>${player.name}</td>
		<td>${f:br(player.comment)}</td>
	</tr>
</table>
<h2>期毎打撃成績</h2>
<div id="tab-b">
    <ul>
	<c:forEach var="pbrDtos" items="${pbrlList}">
		<c:choose>
		<c:when test="${!empty pbrDtos.leagueId}">
			<li><a href="#t-${pbrDtos.leagueId}">${pbrDtos.title}</a></li>
		</c:when>
		</c:choose>
    </c:forEach>
    </ul>
    <c:forEach var="pbrDtos" items="${pbrlList}">
    	<c:choose>
    	<c:when test="${!empty pbrDtos.leagueId}">
		<div id="t-${pbrDtos.leagueId}">
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
				</c:when>
				<c:when test="${empty tbrDtos.gameDate && empty tbrDtos.gameNumber && empty tbrDtos.leagueId}">
				</c:when>
				<c:when test="${empty tbrDtos.gameDate && empty tbrDtos.gameNumber && tbrDtos.leagueId==pbrDtos.leagueId}">
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
				<c:when test="${empty tbrDtos.gameNumber && tbrDtos.leagueId==pbrDtos.leagueId}"></c:when>
				<c:when test="${tbrDtos.leagueId==pbrDtos.leagueId }">
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
				</c:when>
			</c:choose>
		</c:forEach>
	</table>
	</div>
	</c:when>
	</c:choose>
	</c:forEach>
  </div>
  <h2>期毎投球成績</h2>
<div id="tab-p">
    <ul>
	<c:forEach var="pprDtos" items="${pprlList}">
		<c:choose>
		<c:when test="${!empty pprDtos.leagueId}">
			<li><a href="#t-${pprDtos.leagueId}">${pprDtos.title}</a></li>
		</c:when>
		</c:choose>
    </c:forEach>
    </ul>
    <c:forEach var="pprDtos" items="${pprlList}">
    	<c:choose>
    	<c:when test="${!empty pprDtos.leagueId}">
		<div id="t-${pprDtos.leagueId}">
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
				</c:when>
				<c:when test="${empty tprDtos.gameDate && empty tprDtos.gameNumber && empty tprDtos.leagueId}">
				</c:when>
				<c:when test="${empty tprDtos.gameDate && empty tprDtos.gameNumber && tprDtos.leagueId==pprDtos.leagueId}">
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
				<c:when test="${empty tprDtos.gameNumber && tprDtos.leagueId==pprDtos.leagueId}"></c:when>
				<c:when test="${tprDtos.leagueId==pprDtos.leagueId }">
					<tr>
						<td><a href="${f:url('/player/show/') }${tprDtos.playerId}">${tprDtos.name}</a></td>
						<td>
							<a href="${f:url('/gameSummary/show/')}${tprDtos.gameId}">
								<fmt:formatDate value="${tprDtos.gameDate}" pattern="MM/dd" />
								-${tprDtos.gameNumber}対${tprDtos.opponentName}
							</a>
						</td>
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
			</c:choose>
		</c:forEach>
	</table>
	</div>
	</c:when>
	</c:choose>
	</c:forEach>
  </div>
</body>
</html>