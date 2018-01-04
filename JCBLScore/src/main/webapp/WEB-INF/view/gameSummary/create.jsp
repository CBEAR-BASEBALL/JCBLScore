<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/error.css') }" type="text/css" media="print, projection, screen"/>
	<link href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
	<script src="${f:url('/js/jquery-latest.js')}" type="text/javascript"></script>
	<script src="${f:url('/js/jquery-ui.js')}" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			$("#datepicker").datepicker();
			$("#datepicker").datepicker("option", "dateFormat", 'yy/mm/dd');
		});
</script>
	<script type="text/javascript">
	jQuery(function($){
		$('input').keyup(function(){
			var top = new Array();
			var last= new Array();
			var topSum=0;
			var lastSum=0;
			top[0] = parseInt($('input[name=top1st]').val());
			top[1] = parseInt($('input[name=top2nd]').val());
			top[2] = parseInt($('input[name=top3rd]').val());
			top[3] = parseInt($('input[name=top4th]').val());
			top[4] = parseInt($('input[name=top5th]').val());
			last[0] = parseInt($('input[name=bottom1st]').val());
			last[1] = parseInt($('input[name=bottom2nd]').val());
			last[2] = parseInt($('input[name=bottom3rd]').val());
			last[3] = parseInt($('input[name=bottom4th]').val());
			last[4] = parseInt($('input[name=bottom5th]').val());
			for(var i=0;i<5;i++){
				if(isNaN(top[i])){
					top[i]=0;
				}
				if(isNaN(last[i])){
					last[i]=0;
				}
				topSum=topSum+top[i];
				lastSum=lastSum+last[i];
			}
			$('input[name=firstRun]').val(topSum);
			$('input[name=lastRun]').val(lastSum);
		});
		$('form[name=gameSummaryActionForm]').submit(function(){
			var tpa= new Array();
			var atBats= new Array();
			var fourBall= new Array();
			for(var i=0;i<20;i++){
				tpaStr="input[name=\"tpa["+i+"]\"]";
				atBatsStr="input[name=\"atBats["+i+"]\"]";
				fourBallStr="input[name=\"fourBall["+i+"]\"]";
				tpa[i]=$(tpaStr).val();
				atBats[i]=$(atBatsStr).val();
				fourBall[i]=$(fourBallStr).val();
				if(tpa[i]-atBats[i]!=fourBall[i]){
					//str="input[name=\"playerId["+i+"]\"] option:selected";
					//var obj=$(str);
					alert(i+1+"行目のデータが不正です");
					return false;
				}
			}
			return true;
		});
	});
	</script>
</head>
<body>
<html:errors/>
<s:form>
<%--<html:select property="gameYear">
	<c:forEach var="i" begin="2003" end="2025">
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
日--%>
<html:text styleId="datepicker" property="gameDate"/>
<html:hidden property="gameDate" />
第
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
			<html:text property="top5th" value="" size="2" maxlength="2" errorStyleClass="err" />
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
		<th>守備位置</th>
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
				<html:option value="${playerList.id}">${playerList.name}(${playerList.shortName})</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="position">
				<html:option value=""></html:option>
				<html:option value="1">投</html:option>
				<html:option value="2">捕</html:option>
				<html:option value="3">一</html:option>
				<html:option value="4">二</html:option>
				<html:option value="5">外</html:option>
				<html:option value="6">DH</html:option>
			</html:select>
		</td>
		<td>
			<html:text property="tpa[${i-1}]" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="atBats[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="hit[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="homerun[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="rbi[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="fourBall[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="strikeOut[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="twoBase[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
			<html:hidden property="myTeamId[${i-1}]" value="0"/>
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
				<html:option value="${playerList.id}">${playerList.name}(${playerList.shortName})</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:text property="inning1[${i-1}]" value="0" size="1" maxlength="1" errorStyleClass="err" />
			回
			<html:text property="inning2[${i-1}]" value="0" size="1" maxlength="1" errorStyleClass="err" />
			/3
		</td>
		<td>
			<html:text property="p_hit[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_strikeout[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_fourBall[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="runs[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="pa[${i-1}]" value="1" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_homerun[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
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
	<html:hidden property="p_myTeamId[${i-1}]" value="0" />
	</c:forEach>
</table>
<hr>
<h2>後攻打者成績</h2>
<table border=1>
	<tr>
		<th>選手名</th>
		<th>守備位置</th>
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
			<html:select property="playerId">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}(${playerList.shortName})</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:select property="position">
				<html:option value=""></html:option>
				<html:option value="1">投</html:option>
				<html:option value="2">捕</html:option>
				<html:option value="3">一</html:option>
				<html:option value="4">二</html:option>
				<html:option value="5">外</html:option>
				<html:option value="6">DH</html:option>
			</html:select>
		</td>
		<td>
			<html:text property="tpa[${i-1}]" value="" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="atBats[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="hit[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="homerun[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="rbi[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="fourBall[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="strikeOut[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="twoBase[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
			<html:hidden property="myTeamId[${i-1}]" value="1"/>
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
				<html:option value="${playerList.id}">${playerList.name}(${playerList.shortName})</html:option>
			</c:forEach>
			</html:select>
		</td>
		<td>
			<html:text property="inning1[${i-1}]" value="0" size="1" maxlength="1" errorStyleClass="err" />
			回
			<html:text property="inning2[${i-1}]" value="0" size="1" maxlength="1" errorStyleClass="err" />
			/3
		</td>
		<td>
			<html:text property="p_hit[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_strikeOut[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_fourBall[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="runs[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="pa[${i-1}]" value="1" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_homerun[${i-1}]" value="0" size="2" maxlength="2" errorStyleClass="err" />
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
	<html:hidden property="p_myTeamId[${i-1}]" value="1"/>
	</c:forEach>
</table>

<hr>
<s:submit property="createComplete" value="登録"/>
<input type="button" value="戻る" onClick="history.back()">
</s:form>
</body>
</html>