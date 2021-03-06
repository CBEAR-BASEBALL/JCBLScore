<%@page pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="${f:url('/css/style.css') }" type="text/css" media="print, projection, screen"/>
	<%-- jquery --%>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	<%-- bootstrap --%>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	<%-- jquery-ui --%>
	<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" media="print, projection, screen"/>
	<%-- highcharts --%>
	<script type="text/javascript" src="https://code.highcharts.com/4.2.2/highcharts.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/2.2/highcharts-more.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.29.4/js/jquery.tablesorter.min.js" type="text/javascript"></script>
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
    $('form[name=planActionForm]').submit(function(){
    	if($('input:hidden[name="idHidden"]').val() == ""){
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