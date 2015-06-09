<%@page pageEncoding="UTF-8"%>
<html>
<body>
<s:form>
<html:select property="gameYear">
	<c:forEach var="i" begin="2003" end="2016">
		<html:option value="${i}">${i}</html:option>
	</c:forEach>
</html:select>
年
<html:select property="gameMonth">
	<c:forEach var="i" begin="1" end="12">
		<html:option value="${i}">${i}</html:option>
	</c:forEach>
</html:select>
月
<html:select property="gameDay">
	<c:forEach var="i" begin="1" end="31">
		<html:option value="${i}">${i}</html:option>
	</c:forEach>
</html:select>
日第
<html:select property="gameNumber">
	<c:forEach var="i" begin="1" end="10">
		<html:option value="${i}">${i}</html:option>
	</c:forEach>
</html:select>
試合
<hr>
<table border=1>
	<tr>
		<th>チーム名</th>
		<th>1</th>
		<th>2</th>
		<th>3</th>
		<th>4</th>
		<th>5</th>
		<th>R</th>
	</tr>
	<tr>
		<td>
			<html:select property="firstTeam">
			<c:forEach var="teamList" items="${teamList}">
				<html:option value="${teamList.teamId}">${teamList.teamName}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="top1st">
			<c:forEach var="i" begin="0" end="10">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="top2nd">
			<html:option value=""></html:option>
			<c:forEach var="i" begin="0" end="10">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="top3rd">
			<html:option value=""></html:option>
			<c:forEach var="i" begin="0" end="10">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="top4th">
			<html:option value=""></html:option>
			<c:forEach var="i" begin="0" end="10">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="top5th">
			<html:option value=""></html:option>
			<c:forEach var="i" begin="0" end="10">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select></td>
		<td>
			<html:select property="top2nd">
			<c:forEach var="i" begin="0" end="30">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select></td>
	</tr>
	<tr>
		<td>
			<html:select property="lastTeam">
			<c:forEach var="teamList" items="${teamList}">
				<html:option value="${teamList.teamId}">${teamList.teamName}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="bottom1st">
			<c:forEach var="i" begin="0" end="10">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="bottom2nd">
			<html:option value=""></html:option>
			<c:forEach var="i" begin="0" end="10">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="bottom3rd">
			<html:option value=""></html:option>
			<c:forEach var="i" begin="0" end="10">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="bottom4th">
			<html:option value=""></html:option>
			<c:forEach var="i" begin="0" end="10">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="bottom5th">
			<html:option value=""></html:option>
			<c:forEach var="i" begin="0" end="10">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select></td>
		<td>
			<html:select property="top2nd">
			<c:forEach var="i" begin="0" end="30">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select></td>
	</tr>
</table>
</s:form>
</body>
</html>