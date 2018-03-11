<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" href="${f:url('/css/ajaxStats.css') }" type="text/css" media="print, projection, screen"/>
	<!-- jquery -->
	<script type="text/javascript" src="//code.jquery.com/jquery-1.9.1.min.js"></script>
	<!-- <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.4.1.min.js"></script>
	 -->
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.blockUI/2.70/jquery.blockUI.min.js"></script>
	<!-- bootstrap -->
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	<!-- jquery-ui -->
	<script type="text/javascript" src="//code.jquery.com/ui/1.9.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" media="print, projection, screen"/>
	<%-- highcharts --%>
	<script type="text/javascript" src="//code.highcharts.com/4.2.2/highcharts.js"></script>
	<script type="text/javascript" src="//code.highcharts.com/2.2/highcharts-more.js"></script>
	<!-- onsenUI -->
	<!-- <link rel="stylesheet" href="https://unpkg.com/onsenui/css/onsenui.css">
	<link rel="stylesheet" href="https://unpkg.com/onsenui/css/onsen-css-components.min.css">
	<script src="https://unpkg.com/onsenui/js/onsenui.min.js"></script>
	 -->
	<!--datatables -->
	<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$(function(){
			$.blockUI({
				//message: $('#tallContent'),
				message: 'しばらくお待ちください…',
				css: {
					border: 'none',
					padding: '15px',
					backgroundColor: '#fff',
					opacity: .6,
					color: '#333'
				},
				overlayCSS: {
					backgroundColor: '#000',
					opacity: 0.6
				}
			});
		    //setTimeout($.unblockUI, 500);
		});
		$.extend( $.fn.dataTable.defaults, {
	        language: {
	            url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
	        }
	    	})
			$.ajax('/JCBLScore/api/v1/player/show/${player.id}',
			{
				type: 'get',
				dataType: 'json'
			})
			.success(function(data){
				//グラフ用処理
				var category = new Array();
				var average = new Array();
				var slg = new Array();
				var obp = new Array();
				for(var i=0;i<data.playerBattingResultList.length;i++){
					if(data.playerBattingResultList[i].leagueId!=null){
						//console.log(data.playerBattingResultList[i].title);
						category.push(data.playerBattingResultList[i].title);
						average.push(data.playerBattingResultList[i].average);
						slg.push(data.playerBattingResultList[i].slg);
						obp.push(data.playerBattingResultList[i].obp);
					}

				}
				var pSeasons=new Array();
				var era=new Array();
				var whip=new Array();
				for(var j=0;j<data.personalPitchingResultList.length;j++){
					if(data.personalPitchingResultList[j].leagueId!=null){
						pSeasons.push(data.personalPitchingResultList[j].title);
						era.push(data.personalPitchingResultList[j].era);
						whip.push(data.personalPitchingResultList[j].whip);
					}
				}
				var pos=new Array();
				for(var k=0;k<data.posDtos.length;k++){
					pos.push([data.posDtos[k].pos,data.posDtos[k].count]);
				}

				$('#battingResult').DataTable({
					data:data.playerBattingResultList,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "name",
						  render:function(data,type,row){
							if(row.leagueId!=null){
								return data+'('+row.title+')';
							}
							return '<b>'+data+'(通算)</b>';
						  }
							  },
						{ data: "tpa"  },
						{ data: "atBats"  },
						{ data: "hit"  },
						{ data: "homerun"  },
						{ data: "rbi"  },
						{ data: "fourBall"  },
						{ data: "strikeOut"  },
						{ data: "twobase"  },
						{ data: "average",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "slg",
						  render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "ops",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "obp",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "notStrikeOut",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
					],
					columnDefs: [
						{
							targets:[0],
							className:"name"
						},
					],
		        	//order: [[ 10, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#battingByOpponent').DataTable({
					data:data.personalBattingResultGroupByOpponent,
					searching: false,
					//ordering:false,
					paging: false,
					columns: [
						{
							data: "opponentName",
							render:function(data,type,row){
								return 'v.s.'+data;
							}
						},
						{ data: "tpa"  },
						{ data: "atBats"  },
						{ data: "hit"  },
						{ data: "homerun"  },
						{ data: "rbi"  },
						{ data: "fourBall"  },
						{ data: "strikeOut"  },
						{ data: "twobase"  },
						{ data: "average",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "slg",
						  render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "ops",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "obp",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "notStrikeOut",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
					],
					columnDefs: [
						{
							targets:[0],
							className:"name"
						},
					],
		        	//order: [[ 10, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#pitchingResult').DataTable({
					data:data.personalPitchingResultList,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "name",
							render:function(data,type,row){
								if(row.leagueId!=null){
									return data+'('+row.title+')';
								}
								return '<b>'+data+'(通算)</b>';
							}
						},
						{ data: "gameCount"  },
						{ data: "inning",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )},
						{ data: "hit"  },
						{ data: "strikeOut"  },
						{ data: "fourBall"  },
						{ data: "runs"  },
						{ data: "complete"  },
						{ data: "shutout"  },
						{ data: "win"  },
						{ data: "lose" },
						{ data: "save" },
						{ data: "era",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
						{ data: "whip",
						  render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
						{ data: "strikeAvg",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
						{ data: "runSupport",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
					],
					columnDefs: [
						{
							targets:[0],
							className:"name"
						},
					],
		        	//order: [[ 10, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#pitchingByOpponent').DataTable({
					data:data.pprgoList,
					searching: false,
					//ordering:false,
					paging: false,
					columns: [
						{ data: "opponentName",
							render:function(data,type,row){
								return 'v.s.'+data;
							}
						},
						{ data: "gameCount"  },
						{ data: "inning",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )},
						{ data: "hit"  },
						{ data: "strikeOut"  },
						{ data: "fourBall"  },
						{ data: "runs"  },
						{ data: "complete"  },
						{ data: "shutout"  },
						{ data: "win"  },
						{ data: "lose" },
						{ data: "save" },
						{ data: "era",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
					],
					columnDefs: [
						{
							targets:[0],
							className:"name"
						},
					],
		        	//order: [[ 10, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				<c:forEach var="pbrDtos" items="${pbrlList}">
				$('#t-${pbrDtos.leagueId}-batting').DataTable({
					data:data.tbrDtos['${pbrDtos.leagueId}'],
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "name"},
						{ data: "gameDate",
							render:function(data,type,row){
								if(row.gameNumber==null){
									return '<b>'+row.title+'(通算)</b>';
								}else if(row.myteamId!=row.teamId){
									return new Date(data).toLocaleDateString()+'-'+row.gameNumber+'対'+row.opponentName+'(助)';
								}else{
									return new Date(data).toLocaleDateString()+'-'+row.gameNumber+'対'+row.opponentName;
								}
							}
						},
						{ data: "tpa"  },
						{ data: "atBats"  },
						{ data: "hit"  },
						{ data: "homerun"  },
						{ data: "rbi"  },
						{ data: "fourBall"  },
						{ data: "strikeOut"  },
						{ data: "twobase"  },
						{ data: "average",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
					],
					columnDefs: [
						{
							targets:[0],
							className:"name"
						},
						{
							targets:[1],
							className:"name"
						},
					],
		        	//order: [[ 10, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				</c:forEach>
				<c:forEach var="pprDtos" items="${pprlList}">
				$('#t-${pprDtos.leagueId}-pitching').DataTable({
					data:data.tprDtos['${pprDtos.leagueId}'],
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "name"},
						{ data: "gameDate",
							render:function(data,type,row){
								if(row.gameNumber==null){
									return '<b>'+row.title+'(通算)</b>';
								}else if(row.myteamId!=row.teamId){
									return new Date(data).toLocaleDateString()+'-'+row.gameNumber+'対'+row.opponentName+'(助)';
								}else{
									return new Date(data).toLocaleDateString()+'-'+row.gameNumber+'対'+row.opponentName;
								}
							}
						},
						{ data: "inning"  },
						{ data: "hit"  },
						{ data: "strikeOut"  },
						{ data: "fourBall"  },
						{ data: "runs"  },
						{ data: "complete"  },
						{ data: "shutout"  },
						{ data: "win"  },
						{ data: "lose"  },
						{ data: "save"  },
						{ data: "era",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
					],
					columnDefs: [
						{
							targets:[0],
							className:"name"
						},
						{
							targets:[1],
							className:"name"
						},
					],
		        	//order: [[ 10, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				</c:forEach>
				$('#chart_div').highcharts({
						chart: { type: 'line',},
						title: { text: "打撃成績推移"},
						xAxis: {
							categories: category,
							labels: {
								style: { color: '#000000' }
							}
						},
						yAxis: {
								title: { text: null },
								labels: {
									style: { color: '#000000' }
								},
								ceiling: 1,
								floor: 0,
								allowDecimals:true,
								startOnTick: false
						},
						plotOptions: {
							line: {	events: {
								legendItemClick: function () {
									return false;
								}
							}}
						},
						tooltip: {
							shared: true,
							pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y}</b><br/>',
							backgroundColor: '#FFFFFF',
							style: {
								color: '#000000'
							}
						},
						series: [
							{
								name: '打率',
								data: average
							},
							{
								name: '長打率',
								data: slg
							},
							{
								name: '出塁率',
								data: obp
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
						categories:pSeasons,
						labels: { style: { color: '#000000'	} }
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
					series: [
						{
							name: '防御率',
							data: era
						},
						{
							name: 'WHIP',
							data: whip
						}
					]
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
						data: pos
						<%--
						<c:forEach var="posDtos" items="${posDtos}">
							['${posDtos.pos}',${posDtos.count}],
						</c:forEach>
						--%>

					}],
					tooltip: {
						formatter: function() {
							return this.y +'試合';},
					enabled:true
					}
				});

			})
			.always(function() {
				$('#tab-b').tabs();
				$('#tab-p').tabs();
				$.unblockUI();
			});
				})
	</script>
</head>
<body>
<%-- <ons-page>
<ons-scroller style="height: 200px; width:100%">
--%>
<hr>
<h2>選手紹介</h2>
<table border=1>
	<tr>
		<td>${player.name}</td>
		<td class="name">
		<c:choose>
			<c:when test="${!empty teamHistoryDtoList}">
			所属履歴：<br>
				<c:forEach var="thList" items="${teamHistoryDtoList}">
					${thList.startTitle}～${thList.endTitle}：${thList.teamName}<br>
				</c:forEach>
			</c:when>
		</c:choose>

		${f:br(player.comment)}</td>

	</tr>
</table>
<h2>打撃成績</h2>
<table border=1 id="battingResult">
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
			<table border=1 id="t-${pbrDtos.leagueId}-batting">
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
			</table>
		</div>
	</c:when>
	</c:choose>
</c:forEach>
</div>

<h2>対球団別打撃成績</h2>
<table border=1 id="battingByOpponent">
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
</table>
<h2>投球成績</h2>
<table border=1  id="pitchingResult">
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
		<th>援護率</th>
	</tr>
	</thead>
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
			<table border=1 id="t-${pprDtos.leagueId}-pitching">
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
				</tr>
				</thead>
			</table>
		</div>
	</c:when>
	</c:choose>
</c:forEach>
</div>


<h2>対球団別投球成績</h2>
<table border=1 id="pitchingByOpponent">
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
	</tr>
	</thead>
</table>

<div id="chart_div3" style="width: 80%; height: 300px;"></div>
<div id="chart_div" style="width: 80%; height: 300px;"></div>
<div id="chart_div2" style="width: 80%; height: 300px;"></div>
<%--
<!--shinobi1-->
<script type="text/javascript" src="http://x6.karakasa.com/ufo/05087790l"></script>
<noscript><a href="http://x6.karakasa.com/bin/gg?05087790l" target="_blank">
<img src="http://x6.karakasa.com/bin/ll?05087790l" border="0"></a>
<br><span style="font-size:9px">
<img style="margin:0;vertical-align:text-bottom;" src="http://img.shinobi.jp/tadaima/fj.gif" width="19" height="11">
</span>
</noscript>
<!--shinobi2-->
--%>
<%-- </ons-scroller>
</ons-page>--%>
</body>
</html>