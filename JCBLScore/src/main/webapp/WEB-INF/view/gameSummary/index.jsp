<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<link href="${f:url('/css/bootstrap/bootstrap.min.css') }" rel="stylesheet">
	<script src="${f:url('/js/jquery-latest.js')}" type="text/javascript"></script>
	<script src="${f:url('/js/bootstrap/bootstrap.min.js')}" type="text/javascript"></script>
</head>
<body>
<table border=0 CELLSPACING=1 CELLPADDING=5>
	<c:forEach var="gameList" items="${gameListDtos}">
		<c:choose>
			<c:when test="${!empty gameList.title }">
				<tr>
					<td bgcolor="#d3d3d3">${gameList.title}</td>
				</tr>
			</c:when>
		</c:choose>
	<tr>
		<td>
			<font size="-1"><a href="date/${gameList.gameId}">試合結果
			<fmt:formatDate value="${gameList.gameDate}" pattern="yyyy.MM.dd"/></a>
			</font>
		</td>
	</tr>
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