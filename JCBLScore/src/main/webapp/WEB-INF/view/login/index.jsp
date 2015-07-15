<%@page pageEncoding="UTF-8"%>
<html>
<body>
<html:errors/>
<s:form>
ログインID:<html:text property="loginId" size="20" maxlength="20"/>
<br>
パスワード:<html:password property="password" size="20" maxlength="20"/>
<s:submit property="login" value="送信"/>
</s:form>
</body>
</html>