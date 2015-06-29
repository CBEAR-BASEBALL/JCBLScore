<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/error.css') }" type="text/css" media="print, projection, screen"/>
</head>
<body>
<html:errors/>
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
			<html:text property="top1st" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="top2nd" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="top3rd" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="top4th" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="top1st" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="firstRun" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
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
			<html:text property="bottom1st" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="bottom2nd" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="bottom3rd" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="bottom4th" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="bottom5th" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="lastRun" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
	</tr>
</table>
<hr>
<h2>先攻打者成績</h2>
<table border=1>
	<tr>
		<th>選手名</th>
		<th>打席数</th>
		<th>打数</th>
		<th>安打</th>
		<th>本塁打</th>
		<th>打点</th>
		<th>四球</th>
		<th>三振</th>
		<th>二塁打</th>
	</tr>
	<c:forEach var="i" begin="1" end="10">
	<tr>
		<td>
			<html:select property="playerId">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:text property="tpa" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="atBats" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="hit" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="homerun" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="rbi" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="fourBall" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="strikeOut" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="twoBase" value="0" size="2" maxlength="2" errorStyleClass="err" />
			<html:hidden property="myTeamId" value="0"/>
		</td>
	</tr>
	</c:forEach>
</table>
<hr>
<h2>先攻投手成績</h2>
<table border=1>
	<tr>
		<th>勝敗S</th>
		<th>選手名</th>
		<th>投球回</th>
		<th>被安打</th>
		<th>奪三振</th>
		<th>与四球</th>
		<th>失点</th>
		<th>投球人数</th>
		<th>被本</th>
		<th>完投</th>
		<th>完封</th>
	</tr>
	<c:forEach var="i" begin="1" end="4">
	<tr>
		<td>
			<html:select property="result">
				<html:option value=""></html:option>
				<html:option value="1">○</html:option>
				<html:option value="2">●</html:option>
				<html:option value="3">S</html:option>
			</html:select>
		</td>
		<td>
			<html:select property="p_playerId">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:text property="inning1" value="0" size="1" maxlength="1" errorStyleClass="err" />
			回
			<html:text property="inning2" value="0" size="1" maxlength="1" errorStyleClass="err" />
			/3
		</td>
		<td>
			<html:text property="p_hit" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_strikeout" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_fourBall" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="runs" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="pa" value="1" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_homerun" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:select property="complete">
				<html:option value="0">0</html:option>
				<html:option value="1">1</html:option>
			</html:select>
		</td>
		<td>
			<html:select property="shutout">
				<html:option value="0">0</html:option>
				<html:option value="1">1</html:option>
			</html:select>
		</td>
	</tr>
	<html:hidden property="p_myTeamId" value="0"/>
	</c:forEach>
</table>
<hr>
<h2>後攻打者成績</h2>
<table border=1>
	<tr>
		<th>選手名</th>
		<th>打席数</th>
		<th>打数</th>
		<th>安打</th>
		<th>本塁打</th>
		<th>打点</th>
		<th>四球</th>
		<th>三振</th>
		<th>二塁打</th>
	</tr>
	<c:forEach var="i" begin="1" end="10">
	<tr>
		<td>
			<html:select property="playerId">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:text property="tpa" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="atBats" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="hit" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="homerun" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="rbi" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="fourBall" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="strikeOut" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="twoBase" value="0" size="2" maxlength="2" errorStyleClass="err" />
			<html:hidden property="myTeamId" value="1"/>
		</td>
	</tr>
	</c:forEach>
</table>
<hr>
<h2>後攻投手成績</h2>
<table border=1>
	<tr>
		<th>勝敗S</th>
		<th>選手名</th>
		<th>投球回</th>
		<th>被安打</th>
		<th>奪三振</th>
		<th>与四球</th>
		<th>失点</th>
		<th>投球人数</th>
		<th>被本</th>
		<th>完投</th>
		<th>完封</th>
	</tr>
	<c:forEach var="i" begin="1" end="4">
	<tr>
		<td>
			<html:select property="result">
				<html:option value=""></html:option>
				<html:option value="1">○</html:option>
				<html:option value="2">●</html:option>
				<html:option value="3">S</html:option>
			</html:select>
		</td>
		<td>
			<html:select property="p_playerId">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:text property="inning1" value="0" size="1" maxlength="1" errorStyleClass="err" />
			回
			<html:text property="inning2" value="0" size="1" maxlength="1" errorStyleClass="err" />
			/3
		</td>
		<td>
			<html:text property="p_hit" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_strikeOut" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_fourBall" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="runs" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="pa" value="1" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_homerun" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:select property="complete">
				<html:option value="0">0</html:option>
				<html:option value="1">1</html:option>
			</html:select>
		</td>
		<td>
			<html:select property="shutout">
				<html:option value="0">0</html:option>
				<html:option value="1">1</html:option>
			</html:select>
		</td>
	</tr>
	<html:hidden property="p_myTeamId" value="1"/>
	</c:forEach>
</table>

<hr>
<s:submit property="createComplete" value="登録"/>
<input type="button" value="戻る" onClick="history.back()">
</s:form>
</body>
</html>