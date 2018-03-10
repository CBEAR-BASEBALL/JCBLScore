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
				//$('span#regBats').text(data.regAtBats);
				//$('span#regPitch').text(data.regAtPitch);
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
				<c:forEach var="pbrDtos" items="${pbrlList}">
				$('#t-${pbrDtos.leagueId}-table').DataTable({
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
								}
								return new Date(data).toLocaleDateString()+'-'+row.gameNumber+'対'+row.opponentName;
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
			})
			.always(function() {
				$('#tab-b').tabs();
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
<a href="${f:url('/player/detail/')}${player.id}">内訳</a>
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
			<table border=1 id="t-${pbrDtos.leagueId}-table">
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
<%--
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
  --%>
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
		<th>援護率</th>
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
					<td><!-- <fmt:formatNumber value="${tprDtos.runSupport}" pattern="#0.00" />--></td>
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
					<td><fmt:formatNumber value="${tprDtos.runSupport}" pattern="#0.00" /></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</table>
<h2>期毎投球成績</h2>
<a href="${f:url('/player/detail/')}${player.id}">内訳</a>
<%--
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
  --%>
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