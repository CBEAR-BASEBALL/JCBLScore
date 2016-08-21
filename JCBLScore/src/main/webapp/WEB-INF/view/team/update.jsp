<%@page pageEncoding="UTF-8"%>
<html>
<body>
<h1>チーム情報更新</h1>

<s:form>
<p>チーム名：<html:text property="teamName"/></p>
<p>チーム名省略：<html:text property="shortName"/></p>
<s:submit property="updateComplete" value="更新"/>
<input type="button" value="戻る" onClick="history.back()">
<html:hidden property="teamId" value="${teamId}"/>
</s:form>
</body>
</html>