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
・<a href="./team/">チーム紹介</a>
<br>・<a href="./result/">スタッツ</a>
<br>・<a href="./gameSummary/">スコアボード</a>
<c:choose>
	<c:when test="${!empty loginUserDto.authority}">
		<br>・<a href="./gameSummary/create">新規試合入力</a>
		<br>・<a href="./player/">選手新規入力・更新</a>
		<br><b>(${loginUserDto.name}さんログイン中)</b>
	</c:when>
	<c:otherwise>
		<s:form action="login">
			<input type="submit" value="ログイン" class="btn btn-primary"/>
		</s:form>
	</c:otherwise>
</c:choose>
<hr>
ご意見・ご要望・ご質問
<!--ninja_style_mailform-->
<script type="text/javascript" src="http://mf1.shinobi.jp/call/646aed85cc6881f51be70c5e0ba98e15/default.js" charset="utf-8"></script>
<!--ninja_style_mailform-->

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
</body>
</html>