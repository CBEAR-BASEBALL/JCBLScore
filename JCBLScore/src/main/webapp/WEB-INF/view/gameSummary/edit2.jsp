<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="${f:url('/css/error.css') }" type="text/css" media="print, projection, screen"/>
	<%-- jquery --%>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	<%-- bootstrap --%>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	<%-- jquery-ui --%>
	<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" media="print, projection, screen"/>
	<%-- highcharts --%>
	<%-- <script type="text/javascript" src="https://code.highcharts.com/4.2.2/highcharts.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/2.2/highcharts-more.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.29.4/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	--%>
	<script type="text/javascript">
		$(function(){
			$("#datepicker").datepicker();
			$("#datepicker").datepicker("option", "dateFormat", 'yy/mm/dd');
			$("#datepicker").datepicker("setDate",'${gameDate}');

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
					return true;
				}
			}
			return true;
		});
	});
	</script>
	<script type="text/javascript">
	$(function(){
		$('.player').autocomplete({
			source: function( req, res ) {
			$.post({
				url: "/JCBLScore/api/v1/player/search/",
				dataType: "json",
				type:'POST',
				data:{name:req.term},
				success: function( data ) {
					res(data);
				}
			});
		},
		select: function (event, ui) {
			//$("#id").val(ui.item.label);
			var index = $('.player').index(this);
			console.log(index);
			//console.log($('input:hidden[name="playerId"]').eq(index).val());
			$('input:hidden[name="playerId['+index+']"]').val(ui.item.id);
		},
		autoFocus: true,
		delay: 1000,
		minLength: 2
	});
		$('.p_player').autocomplete({
				source: function( req, res ) {
				$.post({
					url: "/JCBLScore/api/v1/player/search/",
					dataType: "json",
					type:'POST',
					data:{name:req.term},
					success: function( data ) {
						res(data);
					}
				});
			},
			select: function (event, ui) {
				//$("#id").val(ui.item.label);
				var index = $('.p_player').index(this);
				console.log(index);
				$('input:hidden[name="p_playerId['+index+']"]').val(ui.item.id);
			},
			autoFocus: true,
			delay: 1000,
			minLength: 2
		});
	//日本語入力をスタートしたら無効化
    $('#id').on('compositionstart', function(){
    	$('#id').autocomplete('disable');
    });

    //日本語入力が確定したら有効化
    $('#id').on('compositionend', function(){
    	$('#id').autocomplete('enable').autocomplete('search');
    });
    $('form[name=gameSummaryActionForm]').submit(function(){
    	if($('input:hidden[name="playerId"]').val() == ""){
    		alert("2文字以上入力して候補から選択してください");
    		return false;
    	}
		return true;

	});

  });
	</script>
</head>
<body>
<html:errors/>
レコードの削除はプレイヤー名「空」かつ打席数「空」。
<s:form action="">
<html:text styleId="datepicker" property="gameDate"/>
<%-- <html:hidden property="gameDate" />--%>
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
			<html:text property="top1st"  size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="top2nd"  size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="top3rd" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="top4th" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="top5th" size="2" maxlength="2" errorStyleClass="err"/>
		</td>
		<td>
			<html:text property="firstRun" size="2" maxlength="2" errorStyleClass="err" />
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
			<html:text property="bottom1st" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="bottom2nd" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="bottom3rd" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="bottom4th" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="bottom5th" size="2" maxlength="2" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="lastRun" size="2" maxlength="2" errorStyleClass="err" />
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
	<c:forEach var="i" begin="1" end="20">
	<tr>
		<td>
			<html:text property="playerName[${i-1}]" styleClass="player"/>
			<html:hidden property="playerId[${i-1}]"/>
			<%--
			<html:select property="playerId[${i-1}]">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}(${playerList.shortName})</html:option>
			</c:forEach>
			</html:select>
			--%>
		</td>
		<td>
			<html:select property="position[${i-1}]">
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
			<html:text property="tpa[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="atBats[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="hit[${i-1 }]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="homerun[${i-1 }]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="rbi[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="fourBall[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="strikeOut[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="twoBase[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
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
	<c:forEach var="i" begin="1" end="8">
	<tr>
		<td>
			<html:text property="win[${i-1}]" size="2" maxlength="2" errorStyleClass="err" />勝
			<html:text property="lose[${i-1}]" size="2" maxlength="2" errorStyleClass="err" />敗
			<html:text property="save[${i-1}]" size="2" maxlength="2" errorStyleClass="err" />S
		</td>
		<td>
			<html:text property="p_playerName[${i-1}]" styleClass="p_player" />
			<html:hidden property="p_playerId[${i-1}]" />
			<%--
			<html:select property="p_playerId[${i-1}]">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}(${playerList.shortName})</html:option>
			</c:forEach>
			</html:select>
			--%>
		</td>
		<td>
			<html:text property="inning1[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
			回
			<html:text property="inning2[${i-1}]" size="1" maxlength="1" errorStyleClass="err" />
			/3
		</td>
		<td>
			<html:text property="p_hit[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_strikeOut[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_fourBall[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="runs[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="pa[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_homerun[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="complete[${i-1}]" size="3" maxlength="3" errorStyleClass="err"/>
		</td>
		<td>
			<html:text property="shutout[${i-1}]"  size="3" maxlength="3" errorStyleClass="err"/>
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
	<c:forEach var="i" begin="21" end="40">
	<tr>
		<td>
			<html:text property="playerName[${i-1}]" styleClass="player"/>
			<html:hidden property="playerId[${i-1}]" />
			<%--
			<html:select property="playerId[${i-1}]">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}(${playerList.shortName})</html:option>
			</c:forEach>
			</html:select>
			--%>
		</td>
		<td>
			<html:select property="position[${i-1}]">
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
			<html:text property="tpa[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="atBats[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="hit[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="homerun[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="rbi[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="fourBall[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="strikeOut[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="twoBase[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
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
	<c:forEach var="i" begin="9" end="16">
	<tr>
		<td>
			<html:text property="win[${i-1}]" size="2" maxlength="2" errorStyleClass="err"/>勝
			<html:text property="lose[${i-1}]" size="2" maxlength="2" errorStyleClass="err"/>敗
			<html:text property="save[${i-1}]" size="2" maxlength="2" errorStyleClass="err"/>S
		</td>
		<td>
			<html:text property="p_playerName[${i-1}]" styleClass="p_player" />
			<html:hidden property="p_playerId[${i-1}]" />
			<%--
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}(${playerList.shortName})</html:option>
			</c:forEach>
			</html:select>
			--%>
		</td>
		<td>
			<html:text property="inning1[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
			回
			<html:text property="inning2[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
			/3
		</td>
		<td>
			<html:text property="p_hit[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_strikeOut[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_fourBall[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="runs[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="pa[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="p_homerun[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="complete[${i-1}]" size="3" maxlength="3" errorStyleClass="err" />
		</td>
		<td>
			<html:text property="shutout[${i-1}]" size="3" maxlength="3" errorStyleClass="err"/>
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