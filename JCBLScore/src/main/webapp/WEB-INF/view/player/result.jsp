<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<!-- AJAX API のロード -->
	<%--
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
	// Visualization API と折れ線グラフ用のパッケージのロード
	google.load("visualization", "1", {packages:["corechart"]});
	// Google Visualization API ロード時のコールバック関数の設定
	google.setOnLoadCallback(drawChart);
	// グラフ作成用のコールバック関数
	function drawChart() {
		// データテーブルの作成
		var data = google.visualization.arrayToDataTable([
			['シーズン','打率','長打率','出塁率'],
		<c:forEach var="pbrDtos" items="${pbrList}">
			<c:choose>
				<c:when test="${empty pbrDtos.leagueId}"></c:when>
				<c:otherwise>
					['${pbrDtos.title}',${pbrDtos.average},${pbrDtos.slg},${pbrDtos.obp}],
				</c:otherwise>
			</c:choose>
		</c:forEach>
		]);
		var data2 = google.visualization.arrayToDataTable([
				['シーズン','防御率','WHIP'],
				<c:forEach var="pprDtos" items="${pprList}">
					<c:choose>
						<c:when test="${empty pprDtos.leagueId}"></c:when>
						<c:otherwise>
							['${pprDtos.title}',${pprDtos.era},${pprDtos.whip}],
						</c:otherwise>
					</c:choose>
				</c:forEach>
				]);
		// グラフのオプションを設定
		var options = {
				title: '打撃成績推移',
				'pointSize':6
				};
		var options2 = {
				title: '投球成績推移',
				'pointSize':6,
				'vAxis': {
			        'direction': -1

			    }

				};
		// LineChart のオブジェクトの作成
		var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
		var chart2 = new google.visualization.LineChart(document.getElementById('chart_div2'));
		// データテーブルとオプションを渡して、グラフを描画
		chart.draw(data, options);
		chart2.draw(data2, options2);
	}
	</script>
	--%>
	<!--highcharts  -->
	<script type="text/javascript" src="${f:url('/js/jquery-latest.js') }"></script>
	<script type="text/javascript" src="${f:url('/js/highcharts.src.js') }"></script>
	<script type="text/javascript" src="${f:url('/js/highcharts-more.src.js') }"></script>
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
		});
	</script>

</head>
<body>
<hr>
<h2>個人成績</h2>
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
<div id="chart_div" style="width: 80%; height: 300px;"></div>
<div id="chart_div2" style="width: 80%; height: 300px;"></div>
</body>
</html>