<%@page pageEncoding="UTF-8"%>
<html>
<body>
<html:errors/>
<s:form>
<html:select property="id">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}(${playerList.shortName})</html:option>
			</c:forEach>
			</html:select>
<table border=1>
<c:forEach items="${mScheduleList}" var="mScheduleList" varStatus="i">
	<tr>
		<td><fmt:formatDate value="${mScheduleList.date}" pattern="yyyy/MM/dd(E)"/></td>
		<td>
		<html:select property="plans">
			<html:option value=""></html:option>
			<html:option value="3">◎できればここ</html:option>
			<html:option value="2">○参加可</html:option>
			<html:option value="1">△検討中</html:option>
			<html:option value="0">×参加不可</html:option>
		</html:select>
		</td>
	</tr>
</c:forEach>
</table>
<s:submit property="createComplete" value="登録"/>
</s:form>
</body>
</html>