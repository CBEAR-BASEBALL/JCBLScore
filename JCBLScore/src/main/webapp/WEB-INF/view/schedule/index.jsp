<%@page pageEncoding="UTF-8"%>
<html>
<head>
<style>
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
	${htmlStr}
</table>
</body>
</html>