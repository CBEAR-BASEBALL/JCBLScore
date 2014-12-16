<%@page pageEncoding="UTF-8"%>
<html>
<body>
<h1>チーム新規登録</h1>

<s:form>
<p>チーム名：<html:text property="teamName"/></p>
<s:submit property="createComplete" value="登録"/>
<input type="button" value="戻る" onClick="history.back()">
</s:form>
</body>
</html>