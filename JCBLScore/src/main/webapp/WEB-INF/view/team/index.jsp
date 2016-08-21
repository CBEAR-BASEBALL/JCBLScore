<%@page pageEncoding="UTF-8"%>
<html>
<body>
<h1>チーム一覧</h1>
<table border=1>
	<c:forEach var="team" items="${teamList}">
	<tr>
		<td>${team.teamName}
		<c:choose>
			<c:when test="${!empty loginUserDto.id}">
				<a href="./update/${team.teamId}/">(編集)</a>
			</c:when>
		</c:choose></td>
		<td><a href="./batting/${team.teamId}/${teamForm.leagueId}">打撃成績</a></td>
		<td><a href="./pitching/${team.teamId}/${teamForm.leagueId}">投球成績</a></td>
	</tr>
	</c:forEach>
</table>
<br>
<c:choose>
	<c:when test="${!empty loginUserDto.id}">
		<s:form action="">
			<input type="button" value="戻る" onClick="history.back()">
			<s:submit property="create" value="新規チーム登録"/>
		</s:form>
		<s:form action="/player/">
			<s:submit property="create" value="新規メンバー登録"/>
		</s:form>
	</c:when>
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