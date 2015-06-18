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
		       }
		   );

	</script>

</head>
<body>
<hr>
<h2>打者成績</h2>
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
		<td align="right">${battingResultList.name}</td>
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
		<td><fmt:formatNumber value="${battingResultList.notStrikeOut}" pattern="0.0000" /></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
<hr>
<h2>投手成績</h2>
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
		<th>勝</th>
		<th>敗</th>
		<th>S</th>
		<th>防御率</th>
		<th>WHIP</th>
		<th>奪三振率</th>

	</tr>
	</thead>
	<tbody>
	<c:forEach var="pitchingResultList" items="${pitchingResultList}">
	<tr>
		<td align="right">${pitchingResultList.name}</td>
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


</body>
</html>