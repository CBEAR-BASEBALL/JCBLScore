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
<s:submit property="createComplete" value="登録"/>
<input type="button" value="戻る" onClick="history.back()">
</s:form>
</body>
</html>