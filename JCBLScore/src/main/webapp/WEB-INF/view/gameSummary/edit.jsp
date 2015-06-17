<%@page pageEncoding="UTF-8"%>
<html>
<body>
<html:errors/>
<s:form action="">
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
			<html:select property="firstRun">
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
			<html:select property="lastRun">
			<c:forEach var="i" begin="0" end="30">
				<html:option value="${i}">${i}</html:option>
			</c:forEach>
			</html:select></td>
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
			<html:select property="playerId[${i-1}]">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="tpa[${i-1}]">
			<html:option value=""></html:option>
			<c:forEach var="j" begin="1" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="atBats[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="hit[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="homerun[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="rbi[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="fourBall[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="strikeOut[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="twoBase[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
			<html:hidden property="myTeamId[${i-1}]" value="0"/>
			<html:hidden property="playerRecordId[${i-1}]" value="${playerRecordId[i-1] }"/>
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
			<html:select property="result[${i-1}]">
				<html:option value=""></html:option>
				<html:option value="1">○</html:option>
				<html:option value="2">●</html:option>
				<html:option value="3">S</html:option>
			</html:select>
		</td>
		<td>
			<html:select property="p_playerId[${i-1}]">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="inning1[${i-1}]">
			<c:forEach var="j" begin="0" end="5">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
			回
			<html:select property="inning2[${i-1}]">
			<c:forEach var="j" begin="0" end="2">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
			/3
		</td>
		<td>
			<html:select property="p_hit[${i-1}]">
			<c:forEach var="j" begin="0" end="30">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="p_strikeOut[${i-1}]">
			<c:forEach var="j" begin="0" end="15">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="p_fourBall[${i-1}]">
			<c:forEach var="j" begin="0" end="15">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="runs[${i-1}]">
			<c:forEach var="j" begin="0" end="30">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="pa[${i-1}]">
			<c:forEach var="j" begin="1" end="40">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="p_homerun[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="complete[${i-1}]">
				<html:option value="0">0</html:option>
				<html:option value="1">1</html:option>
			</html:select>
		</td>
		<td>
			<html:select property="shutout[${i-1}]">
				<html:option value="0">0</html:option>
				<html:option value="1">1</html:option>
			</html:select>
		</td>
	</tr>
	<html:hidden property="p_myTeamId[${i-1}]" value="0"/>
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
	<c:forEach var="i" begin="11" end="20">
	<tr>
		<td>
			<html:select property="playerId[${i-1}]">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="tpa[${i-1}]">
			<html:option value=""></html:option>
			<c:forEach var="j" begin="1" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="atBats[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="hit[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="homerun[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="rbi[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="fourBall[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="strikeOut[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="twoBase[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
			<html:hidden property="myTeamId[${i-1}]" value="1"/>
			<html:hidden property="playerRecordId[${i-1}]" value="${playerRecordId[i-1] }"/>
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
	<c:forEach var="i" begin="5" end="8">
	<tr>
		<td>
			<html:select property="result[${i-1}]">
				<html:option value=""></html:option>
				<html:option value="1">○</html:option>
				<html:option value="2">●</html:option>
				<html:option value="3">S</html:option>
			</html:select>
		</td>
		<td>
			<html:select property="p_playerId[${i-1}]">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="inning1[${i-1}]">
			<c:forEach var="j" begin="0" end="5">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
			回
			<html:select property="inning2[${i-1}]">
			<c:forEach var="j" begin="0" end="2">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
			/3
		</td>
		<td>
			<html:select property="p_hit[${i-1}]">
			<c:forEach var="j" begin="0" end="30">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="p_strikeOut[${i-1}]">
			<c:forEach var="j" begin="0" end="15">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="p_fourBall[${i-1}]">
			<c:forEach var="j" begin="0" end="15">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="runs[${i-1}]">
			<c:forEach var="j" begin="0" end="30">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="pa[${i-1}]">
			<c:forEach var="j" begin="1" end="40">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="p_homerun[${i-1}]">
			<c:forEach var="j" begin="0" end="8">
				<html:option value="${j}">${j}</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="complete[${i-1}]">
				<html:option value="0">0</html:option>
				<html:option value="1">1</html:option>
			</html:select>
		</td>
		<td>
			<html:select property="shutout[${i-1}]">
				<html:option value="0">0</html:option>
				<html:option value="1">1</html:option>
			</html:select>
		</td>
	</tr>
	<html:hidden property="p_myTeamId[${i-1}]" value="1"/>
	</c:forEach>
</table>

<html:hidden property="id" value="${id}"/>
<hr>
<s:submit property="updateComplete" value="更新"/>
<input type="button" value="戻る" onClick="history.back()">
</s:form>
</body>
</html>