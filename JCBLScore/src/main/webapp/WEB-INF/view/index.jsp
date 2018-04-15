<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>JCBLスコア管理システム</title>
<link rel="stylesheet" href="${f:url('/css/top.css') }" type="text/css" media="print, projection, screen"/>
	<%-- jquery --%>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	<%-- bootstrap --%>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	<%-- jquery-ui --%>
	<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<%-- <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" media="print, projection, screen"/>
	--%><%-- highcharts --%>
	<%--<script type="text/javascript" src="https://code.highcharts.com/4.2.2/highcharts.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/2.2/highcharts-more.js"></script>--%>
</head>
<body>
<!--shinobi1-->
<script type="text/javascript" src="http://x6.karakasa.com/ufo/05087790l"></script>
<noscript><a href="http://x6.karakasa.com/bin/gg?05087790l" target="_blank">
<img src="http://x6.karakasa.com/bin/ll?05087790l" border="0"></a><br>
<span style="font-size:9px"><img style="margin:0;vertical-align:text-bottom;" src="http://img.shinobi.jp/tadaima/fj.gif" width="19" height="11"> </span></noscript>
<!--shinobi2-->
<hr>
<h2>JCBLスコア管理システム</h2>
<img src="${f:url('img/field.jpg')}" width=80%>
<a href="${f:url('/team/')}" class="btn btn-default" id="team">チーム紹介</a>
<a href="${f:url('/result/')}" class="btn btn-default" id="stats">スタッツ</a>
<a href="${f:url('/gameSummary/')}" class="btn btn-default" id="score">スコアボード</a>
<a href="${f:url('/player/')}" class="btn btn-default" id="player">選手一覧</a>
<a href="${f:url('/titleHolder/')}" class="btn btn-default" id="title">タイトルホルダー</a>

<%-- <a href="${f:url('/team/')}" class="btn btn-success">チーム紹介</a>
<a href="${f:url('/result/')}" class="btn btn-success">スタッツ</a>
<a href="${f:url('/gameSummary/')}" class="btn btn-success">スコアボード</a>
<a href="${f:url('/player/')}" class="btn btn-success">選手一覧</a>
--%><br>
<br>
<br>
<br>
<br>
<c:choose>
	<c:when test="${!empty loginUserDto.authority}">
		<a href="./gameSummary/create" class="btn btn-warning" id="gameCreate">新規試合入力</a>
		<a href="${f:url('./player/') }" class="btn btn-warning" id="playerCreate">選手新規入力・更新</a>
		<a href="${f:url('./teamHistory/') }" class="btn btn-warning" id="teamHistory">チーム履歴新規入力・更新</a>
		<br><span class="btn btn-warning" id="loginNow"><b>(${loginUserDto.name}さんログイン中)</b></span>
	</c:when>
	<c:otherwise>
		<s:form action="login">
			<input type="submit" value="ログイン" class="btn btn-danger" id="login"/>
		</s:form>
	</c:otherwise>
</c:choose>
<hr>
<hr>

<%--
<script src="https://embed.small.chat/T25NYBSGZG8LLE0A9J.js" async></script>
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
--%>
</body>
</html>