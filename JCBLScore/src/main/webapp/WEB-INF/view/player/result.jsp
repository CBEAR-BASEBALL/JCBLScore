<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<link rel="stylesheet" href="${f:url('/css/jquery-ui.css') }" type="text/css" media="print, projection, screen"/>
	<!--highcharts  -->
	<script type="text/javascript" src="${f:url('/js/jquery-latest.js') }"></script>
	<script type="text/javascript" src="${f:url('/js/jquery-ui.js') }"></script>
	<script type="text/javascript" src="${f:url('/js/highcharts.src.js') }"></script>
	<script type="text/javascript" src="${f:url('/js/highcharts-more.src.js') }"></script>
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
		       }
		   );

	</script>
	<script>
	$(function() {
		$( "#tab-b" ).tabs();
	});
	$(function() {
		$( "#tab-p" ).tabs();
	});
	</script>
	<script>
		$(function(){
			$('#chart_div').highcharts({
				chart: {
					type: 'line',
				},
				title: {
					text: "打撃成績推移"
				},
				xAxis: {
					categories: [
						<c:forEach var="pbrDtos" items="${pbrList}">
							<c:choose>
								<c:when test="${empty pbrDtos.leagueId}"></c:when>
								<c:otherwise>
									"${pbrDtos.title}",
								</c:otherwise>
							</c:choose>
						</c:forEach>
						],
					labels: {
						style: {
							color: '#000000'
						}
					}
				},
				yAxis: {
					title: {
						text: null
					},
						labels: {
							style: {
								color: '#000000'
							}
						},
						ceiling: 1,
						floor: 0,
						allowDecimals:true,
						startOnTick: false
				},
				plotOptions: {
					line: {
						events: {
							legendItemClick: function () {
								return false;
							}
						}
					}
				},
				tooltip: {
					shared: true,
					pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y}</b><br/>',
					backgroundColor: '#FFFFFF',
					style: {
						color: '#000000'
					}
				},
				series: [{
					name: '打率',
					data: [
						<c:forEach var="pbrDtos" items="${pbrList}">
							<c:choose>
								<c:when test="${empty pbrDtos.leagueId}"></c:when>
								<c:otherwise>
									${pbrDtos.average},
								</c:otherwise>
							</c:choose>
						</c:forEach>
					]
					}, {
					name: '長打率',
					data: [
						<c:forEach var="pbrDtos" items="${pbrList}">
							<c:choose>
								<c:when test="${empty pbrDtos.leagueId}"></c:when>
								<c:otherwise>
									${pbrDtos.slg},
								</c:otherwise>
							</c:choose>
						</c:forEach>
				]
					}, {
					name: '出塁率',
					data: [
						<c:forEach var="pbrDtos" items="${pbrList}">
							<c:choose>
								<c:when test="${empty pbrDtos.leagueId}"></c:when>
								<c:otherwise>
									${pbrDtos.obp},
								</c:otherwise>
							</c:choose>
						</c:forEach>
					]
				    }]
			});
			$('#chart_div2').highcharts({
				chart: {
					type: 'line',
				},
				title: {
					text: "投球成績推移"
				},
				xAxis: {
					categories: [
						<c:forEach var="pbrDtos" items="${pbrList}">
							<c:choose>
								<c:when test="${empty pbrDtos.leagueId}"></c:when>
								<c:otherwise>
									"${pbrDtos.title}",
								</c:otherwise>
							</c:choose>
						</c:forEach>
						],
					labels: {
						style: {
							color: '#000000'
						}
					}
				},
				yAxis: {
					title: {
						text: null
					},
					reversed:true,
						labels: {
							style: {
								color: '#000000'
							}
						},

						<%--
						ceiling: 0,
						floor: 0,
						--%>
						allowDecimals:true,
						startOnTick: false
				},
				plotOptions: {
					line: {
						events: {
							legendItemClick: function () {
								return false;
							}
						}
					}
				},
				tooltip: {
					shared: true,
					pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y}</b><br/>',
					backgroundColor: '#FFFFFF',
					style: {
						color: '#000000'
					}
				},
				series: [{
					name: '防御率',
					data: [
						<c:forEach var="pprDtos" items="${pprList}">
							<c:choose>
								<c:when test="${empty pprDtos.leagueId}"></c:when>
								<c:otherwise>
									${pprDtos.era},
								</c:otherwise>
							</c:choose>
						</c:forEach>
					]
					}, {
					name: 'WHIP',
					data: [
						<c:forEach var="pprDtos" items="${pprList}">
							<c:choose>
								<c:when test="${empty pprDtos.leagueId}"></c:when>
								<c:otherwise>
									${pprDtos.whip},
								</c:otherwise>
							</c:choose>
						</c:forEach>
				]
					}]
			});
			$('#chart_div3').highcharts({
				title: {
					text: '守備位置の割合'
				},
				plotOptions: {
					pie: {
						dataLabels: {
							formatter: function() {
								return '<b>'+ this.point.name +'</b>:'+ Math.round(this.percentage*10)/10 + '%';}
							}
						}
					},
				series: [{
					type: 'pie',
					name: '',
					data: [
					<c:forEach var="posDtos" items="${posDtos}">
						['${posDtos.pos}',${posDtos.count}],
					</c:forEach>
					]
				}],
				tooltip: {
					formatter: function() {
						return this.y +'試合';},
				enabled:true
				}
				});
		});

	</script>

</head>
<body>
<hr>
<h2>選手紹介</h2>
<table border=1>
	<tr>
		<td>${player.name}</td>
		<td>${f:br(player.comment)}</td>
	</tr>
</table>
<h2>打撃成績</h2>
<table border=1 class="tablesorter">
	<thead>
	<tr>
		<th>名前</th>
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
	<c:forEach var="pbrDtos" items="${pbrList}">
		<c:choose>
			<c:when test="${empty pbrDtos.leagueId}">
				<tr>
					<td><b>${pbrDtos.name}(通算)</b></td>
					<td>${pbrDtos.tpa}</td>
					<td>${pbrDtos.atBats}</td>
					<td>${pbrDtos.hit}</td>
					<td>${pbrDtos.homerun}</td>
					<td>${pbrDtos.rbi}</td>
					<td>${pbrDtos.fourBall}</td>
					<td>${pbrDtos.strikeOut}</td>
					<td>${pbrDtos.twobase}</td>
					<td><fmt:formatNumber value="${pbrDtos.average}" pattern="0.0000" /></td>
					<td><fmt:formatNumber value="${pbrDtos.slg}" pattern="0.0000" /></td>
					<td><fmt:formatNumber value="${pbrDtos.ops}" pattern="0.0000" /></td>
					<td><fmt:formatNumber value="${pbrDtos.obp}" pattern="0.0000" /></td>
					<td><fmt:formatNumber value="${pbrDtos.notStrikeOut}" pattern="#0.00" /></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>${pbrDtos.name}(${pbrDtos.title})</td>
					<td>${pbrDtos.tpa}</td>
					<td>${pbrDtos.atBats}</td>
					<td>${pbrDtos.hit}</td>
					<td>${pbrDtos.homerun}</td>
					<td>${pbrDtos.rbi}</td>
					<td>${pbrDtos.fourBall}</td>
					<td>${pbrDtos.strikeOut}</td>
					<td>${pbrDtos.twobase}</td>
					<td><fmt:formatNumber value="${pbrDtos.average}" pattern="0.0000" /></td>
					<td><fmt:formatNumber value="${pbrDtos.slg}" pattern="0.0000" /></td>
					<td><fmt:formatNumber value="${pbrDtos.ops}" pattern="0.0000" /></td>
					<td><fmt:formatNumber value="${pbrDtos.obp}" pattern="0.0000" /></td>
					<td><fmt:formatNumber value="${pbrDtos.notStrikeOut}" pattern="#0.00" /></td>
		</tr>
			</c:otherwise>
		</c:choose>
	</c:forEach>
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
<h2>対球団別打撃成績</h2>
<table border=1 class="tablesorter" id="battingAll">
	<thead>
	<tr>
		<th>名前</th>
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
	<c:forEach var="pbrDtos" items="${pbrgoList}">
		<tr>
			<td>v.s.${pbrDtos.opponentName}</td>
			<td>${pbrDtos.tpa}</td>
			<td>${pbrDtos.atBats}</td>
			<td>${pbrDtos.hit}</td>
			<td>${pbrDtos.homerun}</td>
			<td>${pbrDtos.rbi}</td>
			<td>${pbrDtos.fourBall}</td>
			<td>${pbrDtos.strikeOut}</td>
			<td>${pbrDtos.twobase}</td>
			<td><fmt:formatNumber value="${pbrDtos.average}" pattern="0.0000" /></td>
			<td><fmt:formatNumber value="${pbrDtos.slg}" pattern="0.0000" /></td>
			<td><fmt:formatNumber value="${pbrDtos.ops}" pattern="0.0000" /></td>
			<td><fmt:formatNumber value="${pbrDtos.obp}" pattern="0.0000" /></td>
			<td><fmt:formatNumber value="${pbrDtos.notStrikeOut}" pattern="#0.00" /></td>
		</tr>
	</c:forEach>

</table>
<h2>投球成績</h2>
<table border=1 class="tablesorter">
	<thead>
	<tr>
		<th>名前</th>
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
		<th>WHIP</th>
		<th>奪三振率</th>
	</tr>
	</thead>
	<c:forEach var="tprDtos" items="${pprList}">
		<c:choose>
			<c:when test="${empty tprDtos.leagueId}">
				<tr>
					<td><b>${tprDtos.name}(通算)</b></td>
					<td>${tprDtos.gameCount}</td>
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
					<td><fmt:formatNumber value="${tprDtos.whip}" pattern="#0.00" /></td>
					<td><fmt:formatNumber value="${tprDtos.strikeAvg}" pattern="#0.00" /></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>${tprDtos.name}(${tprDtos.title})</td>
					<td>${tprDtos.gameCount}</td>
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
					<td><fmt:formatNumber value="${tprDtos.whip}" pattern="#0.00" /></td>
					<td><fmt:formatNumber value="${tprDtos.strikeAvg}" pattern="#0.00" /></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</table>
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
<h2>対球団別投球成績</h2>
<table border=1 class="tablesorter" id="pitchingAll">
	<thead>
	<tr>
		<th>名前</th>
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
		<th>WHIP</th>
		<th>奪三振率</th>
	</tr>
	</thead>
	<c:forEach var="pprDtos" items="${pprgoList}">
		<tr>
			<td>v.s.${pprDtos.opponentName}</td>
			<td>${pprDtos.gameCount}</td>
			<td><fmt:formatNumber value="${pprDtos.inning}" pattern="0.000" /></td>
			<td>${pprDtos.hit}</td>
			<td>${pprDtos.strikeOut}</td>
			<td>${pprDtos.fourBall}</td>
			<td>${pprDtos.runs}</td>
			<td>${pprDtos.complete}</td>
			<td>${pprDtos.shutout}</td>
			<td>${pprDtos.win}</td>
			<td>${pprDtos.lose}</td>
			<td>${pprDtos.save}</td>
			<td><fmt:formatNumber value="${pprDtos.era}" pattern="0.00" /></td>
			<td><fmt:formatNumber value="${pprDtos.whip}" pattern="0.00" /></td>
			<td><fmt:formatNumber value="${pprDtos.strikeAvg}" pattern="0.00" /></td>
		</tr>
	</c:forEach>

</table>
<div id="chart_div3" style="width: 80%; height: 300px;"></div>
<div id="chart_div" style="width: 80%; height: 300px;"></div>
<div id="chart_div2" style="width: 80%; height: 300px;"></div>
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