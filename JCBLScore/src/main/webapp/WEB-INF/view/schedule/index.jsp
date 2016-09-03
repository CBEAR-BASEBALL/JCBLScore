<%@page pageEncoding="UTF-8"%>
<html>
<head>
<style>
table td{
	font-size:12px;
	text-align:center;
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
			<td>
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
	<%-- <c:choose>
									<c:when test="${planList.plans==0}">
										<td>×</td>
									</c:when>
									<c:when test="${planList.plans==1}">
										<td id="selection01">△</td>
									</c:when>
									<c:when test="${planList.plans==2}">
										<td id="selection02">○</td>
									</c:when>
									<c:when test="${planList.plans==3}">
										<td id="selection03">◎</td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
									</c:choose>--%>
		<%-- <c:forEach items="${planList}" var="planList">
			<c:choose>
				<c:when test="${playerList.Id==planList.playerId}">
					<tr>
						<td class="left-box"><img width="15" height="15" src="../img/${planList.teamId}.jpg">${planList.name}</td>
					</tr>
				</c:when>
			</c:choose>
			</c:forEach>--%>
	${htmlStr}
</table>
</body>
</html>