<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>JCBLスコア管理システム</title>
<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
<link href="${f:url('/css/bootstrap/bootstrap.min.css') }" rel="stylesheet">
<script src="${f:url('/js/jquery-latest.js')}" type="text/javascript"></script>
<script src="${f:url('/js/bootstrap/bootstrap.min.js')}" type="text/javascript"></script>
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
<a href="${f:url('/team/')}" class="btn btn-success">チーム紹介</a>
<a href="${f:url('/result/')}" class="btn btn-success">スタッツ</a>
<a href="${f:url('/gameSummary/')}" class="btn btn-success">スコアボード</a>
<br>
<br>
<br>
<br>
<br>
<c:choose>
	<c:when test="${!empty loginUserDto.authority}">
		<a href="./gameSummary/create" class="btn btn-warning">新規試合入力</a>
		<a href="./player/" class="btn btn-warning">選手新規入力・更新</a>
		<br><b>(${loginUserDto.name}さんログイン中)</b>
	</c:when>
	<c:otherwise>
		<s:form action="login">
			<input type="submit" value="ログイン" class="btn btn-danger"/>
		</s:form>
	</c:otherwise>
</c:choose>
<hr>
<hr>
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
</body>
</html>