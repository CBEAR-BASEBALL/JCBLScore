<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" href="${f:url('/css/titleHolder.css') }" type="text/css" media="print, projection, screen"/>
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
	<%--
	<!--datatables -->
	<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<meta name="viewport" content="width=device-width,initial-scale=1">--%>
</head>
<body>
<h2>年度別タイトルホルダー</h2>
<table border=1>
	<tr>
		<th>年度</th>
		<th></th>
		<th>打率</th>
		<th>HR</th>
		<th>打点</th>
		<th>安打数</th>
		<th>防御率</th>
		<th>勝利</th>
		<th>セーブ</th>
		<th>奪三振</th>
	</tr>
	<c:forEach var="titleHolderList" items="${titleHolderList}">
	<c:if test="${titleHolderList.totalFlg==1}">
		<tr>
		<td class="total">${titleHolderList.shortTitle}</td>
		<td class="total"></td>
		<td class="total">${titleHolderList.avgPlayer}<br><fmt:formatNumber value="${titleHolderList.average}" pattern="0.000"/></td>
		<td class="total">${titleHolderList.hrPlayer}<br>${titleHolderList.homerun}本</td>
		<td class="total">${titleHolderList.rbiPlayer}<br>${titleHolderList.rbi}打点</td>
		<td class="total">${titleHolderList.hitPlayer}<br>${titleHolderList.hit}安打</td>
		<td class="total">${titleHolderList.eraPlayer}<br><fmt:formatNumber value="${titleHolderList.era}" pattern="#0.00"/></td>
		<td class="total">${titleHolderList.winPlayer}<br>${titleHolderList.win}勝</td>
		<td class="total">${titleHolderList.savePlayer}<br>${titleHolderList.save}S</td>
		<td class="total">${titleHolderList.strikePlayer}<br>${titleHolderList.strikeout}個</td>
		</tr>
	</c:if>
	<c:if test="${titleHolderList.totalFlg==0}">
		<tr>
		<td>${titleHolderList.shortTitle}</td>
		<td></td>
		<td>${titleHolderList.avgPlayer}<br><fmt:formatNumber value="${titleHolderList.average}" pattern="0.000"/></td>
		<td>${titleHolderList.hrPlayer}<br>${titleHolderList.homerun}本</td>
		<td>${titleHolderList.rbiPlayer}<br>${titleHolderList.rbi}打点</td>
		<td>${titleHolderList.hitPlayer}<br>${titleHolderList.hit}安打</td>
		<td>${titleHolderList.eraPlayer}<br><fmt:formatNumber value="${titleHolderList.era}" pattern="#0.00"/></td>
		<td>${titleHolderList.winPlayer}<br>${titleHolderList.win}勝</td>
		<td>${titleHolderList.savePlayer}<br>${titleHolderList.save}S</td>
		<td>${titleHolderList.strikePlayer}<br>${titleHolderList.strikeout}個</td>
	</tr>
	</c:if>

	</c:forEach>
</table>
</body>
</html>