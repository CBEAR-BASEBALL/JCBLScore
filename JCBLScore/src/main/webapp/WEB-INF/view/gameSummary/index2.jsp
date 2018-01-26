<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<%-- jquery --%>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
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
</head>
<body>
<c:forEach var="gameList" items="${gameListDtos}">
	<br>第${gameList.gameNumber}試合
	<table border=0 width="600">
		<tr>
		<td>
			<b>${gameList.team2.teamName}</b>
			<b> <a href="../show/${gameList.gameId}">
			${gameList.lastRun}-${gameList.firstRun}</a></b>
			<b> ${gameList.team.teamName}</b>
			<c:choose>
				<c:when test="${!empty loginUserDto.id}">
					(<a href="../edit/${gameList.gameId}">編集</a>)
				</c:when>
			</c:choose>
		</td>
		</tr>
		<tr>
			<td>
				<c:if test="${gameList.firstRun!=gameList.lastRun}">
					勝　${gameList.win.name}
					<c:if test="${gameList.win.win!=0}">${gameList.win.win}勝</c:if>
					<c:if test="${gameList.win.lose!=0}">${gameList.win.lose}敗</c:if>
					<c:if test="${gameList.win.save!=0}">${gameList.win.save}S</c:if>
					<br>
					<c:if test="${!empty gameList.save.save}">
						Ｓ　${gameList.save.name}
						<c:if test="${gameList.save.win!=0}">${gameList.save.win}勝</c:if>
						<c:if test="${gameList.save.lose!=0}">${gameList.save.lose}敗</c:if>
						<c:if test="${gameList.save.save!=0}">${gameList.save.save}S</c:if>
						<br>
					</c:if>
					敗　${gameList.lose.name}
					<c:if test="${gameList.lose.win!=0}">${gameList.lose.win}勝</c:if>
					<c:if test="${gameList.lose.lose!=0}">${gameList.lose.lose}敗</c:if>
					<c:if test="${gameList.lose.save!=0}">${gameList.lose.save}S</c:if>
					<br>
				</c:if>
				<c:if test="${!empty gameList.homerun}">
				HR　
				<c:forEach var="homerun" items="${gameList.homerun}">
					${homerun.name}
					<c:forEach var="i" begin="1" end="${homerun.homerunCount}" >
					${homerun.homerun-homerun.homerunCount+i}
					<c:if test="${i<homerun.homerunCount}">,</c:if>
					</c:forEach>
					号

				</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td bgcolor="#ffe4e1"><b>寸評:${f:br(f:h(gameList.comment))}</b></td>
		</tr>
	</table>
</c:forEach>
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