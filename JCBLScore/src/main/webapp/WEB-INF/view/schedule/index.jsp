<%@page pageEncoding="UTF-8"%>
<html>
<head>
<style>
table td{
	font-size:12px;
	text-align:center;
	border:1px solid #d2d2d2;
}
td .choice_date{
	width:110px;
}
.left-box{
	text-align:left;
}
#selection01{
	background:#eef0f0;
}
#selection02{
	background:#ffe6ec;
}
#selection03{
	background:#ff78a0;
	color:#fff;
}
</style>
<title>JCBLスコア管理システム：日程調整＆天気予報</title>
<script src="${f:url('/js/jquery-latest.js')}" type="text/javascript"></script>
<script>
$(function(){
	var t=$(".hoge")
	for(var i=0;i<t.length;i++){
		t[i].innerText=t[i].innerText.replace(/\n\r/g,"");
		if(t[i].innerText!=""){
			if(t[i].innerText==3){
				t[i].id="selection03";
				t[i].innerText="◎";
			}else if(t[i].innerText==2){
				t[i].id="selection02";
				t[i].innerText="○";
			}else if(t[i].innerText==1){
				t[i].id="selection01";
				t[i].innerText="△";
			}else if(t[i].innerText==0){
				t[i].innerText="×";
			}
		}
	}
});
</script>
</head>
<body>
<table border=1>
	<tr>
		<td></td>
		<c:forEach items="${scheduleList}" var="scheduleList">
			<td class="choice_date">
			<fmt:formatDate value="${scheduleList.date}" pattern="yyyy/MM/dd(E)"/>
			<br><img src="http://vortex.accuweather.com/adc2010/images/icons-numbered/${scheduleList.weatherdto.img}.png">
			<br>${scheduleList.weatherdto.weather}
			<br><font color="red">${scheduleList.weatherdto.highTemp}</font>/<font color="blue">${scheduleList.weatherdto.lowTemp}</font>
			</td>
		</c:forEach>
	</tr>
	<c:forEach items="${playerList}" var="playerList">
	<tr>
		<td class="left-box"><img width="15" height="15" src="../img/${playerList.teamId}.jpg">${playerList.name}</td>
		<c:forEach items="${scheduleList}" var="scheduleList">
		<td class="hoge">
			<c:forEach items="${planList}" var="planList">
				<c:choose>
					<c:when test="${playerList.Id==planList.playerId && scheduleList.id==planList.mstId}">
						${planList.plans}
					</c:when>
				</c:choose>
			</c:forEach>
		</td>
		</c:forEach>
	</tr>
	</c:forEach>
</table>
<c:choose>
	<c:when test="${!empty loginUserDto.id}">
		<s:form action="">
			<s:submit property="create" value="新規スケジュールマスタ入力"/>
		</s:form>
	</c:when>
</c:choose>
<s:form action="/plan/">
	<s:submit property="create" value="新規スケジュール入力"/>
</s:form>
</body>
</html>