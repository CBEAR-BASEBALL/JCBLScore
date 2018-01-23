<%@page pageEncoding="UTF-8"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.min.js"></script>
<script type="text/javascript">
$(function(){
    $('#id').autocomplete({
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
            $('input:hidden[name="idHidden"]').val(ui.item.id);
        },
        autoFocus: true,
        delay: 2000,
        minLength: 2
    });
  });
</script>
</head>
<body>
<html:errors/>
<s:form>
<html:text property="id" styleId="id"></html:text>
<html:hidden property="idHidden"/>
<%-- <html:select property="id">
			<html:option value=""></html:option>
			<c:forEach var="playerList" items="${playerList}">
				<html:option value="${playerList.id}">${playerList.name}(${playerList.shortName})</html:option>
			</c:forEach>
			</html:select>
			--%>
<table border=1>
<c:forEach items="${mScheduleList}" var="mScheduleList" varStatus="i">
	<tr>
		<td><fmt:formatDate value="${mScheduleList.date}" pattern="yyyy/MM/dd(E)"/></td>
		<td>
		<html:select property="plans">
			<html:option value=""></html:option>
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