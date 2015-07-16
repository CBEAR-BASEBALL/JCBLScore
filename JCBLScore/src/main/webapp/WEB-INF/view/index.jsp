<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>JCBLスコア管理システム</title>
</head>
<body>
<h2>JCBLスコア管理システム</h2>
・<a href="./team/">チーム紹介</a>
<br>・<a href="./result/">スタッツ</a>
<br>・<a href="./gameSummary/">スコアボード</a>
<c:choose>
	<c:when test="${!empty loginUserDto.authority}">
		<br>・<a href="./gameSummary/create">新規試合入力</a>
		<br>・<a href="./player/create">新規選手入力</a>
		<br><b>(${loginUserDto.name}さんログイン中)</b>
	</c:when>
	<c:otherwise>
		<s:form action="login">
			<s:submit property="" value="ログイン"/>
		</s:form>
	</c:otherwise>
</c:choose>
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