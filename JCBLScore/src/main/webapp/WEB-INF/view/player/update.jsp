<%@page pageEncoding="UTF-8"%>
<html>
<body>

<s:form>
<html:errors/>
選手名：<html:text property="name"/>
<html:select property="teamId">
		<c:forEach var="teamList" items="${teamList}" varStatus="status">
			<html:option value="${teamList.teamId}">${teamList.teamName}</html:option>
		</c:forEach>
	</html:select>
	<html:hidden property="id" value="${id}"/>
<s:submit property="updateComplete" value="更新"/>
<input type="button" value="戻る" onClick="history.back()">
</s:form>
<table border=1>
	<tr>
		<th>チーム名</th>
		<th>メンバー</th>
	</tr>
<c:forEach var="teamList" items="${teamList}" >
	<tr>

		<td>${teamList.teamName}</td>
		<td>
		<c:forEach var="playerList" items="${playerList}" >
			<c:choose>
				<c:when test="${teamList.teamId==playerList.teamId}">${playerList.name},</c:when>
			</c:choose>
		</c:forEach>
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
</body>
</html>