<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" href="${f:url('/css/ajaxStats.css') }" type="text/css" media="print, projection, screen"/>
	<!-- jquery -->
	<script type="text/javascript" src="//code.jquery.com/jquery-1.9.1.min.js"></script>
	<!-- <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.4.1.min.js"></script>
	 -->
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.blockUI/2.70/jquery.blockUI.min.js"></script>
	<!-- bootstrap -->
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	<!-- jquery-ui -->
	<script type="text/javascript" src="//code.jquery.com/ui/1.9.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" media="print, projection, screen"/>
	<!-- onsenUI -->
	<!-- <link rel="stylesheet" href="https://unpkg.com/onsenui/css/onsenui.css">
	<link rel="stylesheet" href="https://unpkg.com/onsenui/css/onsen-css-components.min.css">
	<script src="https://unpkg.com/onsenui/js/onsenui.min.js"></script>
	 -->
	<!--datatables -->
	<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function() {
			$.fn.dataTable.ext.errMode = 'none';
			$.extend( $.fn.dataTable.defaults, {
	        language: {
	            url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
	        }
	    	})
			$.ajax('/JCBLScore/api/v1/result/season/${league.id}',
			{
				type: 'get',
				dataType: 'json'
			})

			.success(function(data){
				$('span#regBats').text(data.regAtBats);
				$('span#regPitch').text(data.regAtPitch);
				$('#averageTop10').DataTable({
					data:data.averageTop10,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "rank" },
						{
							data: "name",
							render: function ( data, type, row ) {
								return '<img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
							}
						},
						{ data: "tpa"  },
						{ data: "atBats"  },
						{ data: "hit"  },
						{ data: "homerun"  },
						{ data: "rbi"  },
						{ data: "fourBall"  },
						{ data: "strikeOut"  },
						{ data: "twobase"  },
						{ data: "average",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
					],
					columnDefs: [
						{
							targets: [1],
							className:"name"
						},{
							targets: [10],
							className:"important"
						}
					],
		        	order: [[ 10, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#HRTop10').DataTable({
					data:data.homerunTop10,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "rank" },
						{
							data: "name",
							render: function ( data, type, row ) {
								return '<img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
							}
						},
						{ data: "tpa"  },
						{ data: "atBats"  },
						{ data: "hit"  },
						{ data: "homerun"  },
						{ data: "rbi"  },
						{ data: "fourBall"  },
						{ data: "strikeOut"  },
						{ data: "twobase"  },
						{ data: "average",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
					],
					columnDefs: [
						{
							targets: [1],
							className:"name"
						},{
							targets: [5],
							className:"important"
						}

					],
		        	order: [[ 5, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#RbiTop10').DataTable({
					data:data.rbiTop10,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "rank" },
						{
							data: "name",
							render: function ( data, type, row ) {
								return '<img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
							}
						},
						{ data: "tpa"  },
						{ data: "atBats"  },
						{ data: "hit"  },
						{ data: "homerun"  },
						{ data: "rbi"  },
						{ data: "fourBall"  },
						{ data: "strikeOut"  },
						{ data: "twobase"  },
						{ data: "average",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
					],
					columnDefs: [
						{
							targets: [1],
							className:"name"
						},{
							targets: [6],
							className:"important"
						}
					],
		        	order: [[ 6, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#hitTop10').DataTable({
					data:data.hitTop10,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "rank" },
						{
							data: "name",
							render: function ( data, type, row ) {
								return '<img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
							}
						},
						{ data: "tpa"  },
						{ data: "atBats"  },
						{ data: "hit"  },
						{ data: "homerun"  },
						{ data: "rbi"  },
						{ data: "fourBall"  },
						{ data: "strikeOut"  },
						{ data: "twobase"  },
						{ data: "average",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
					],
					columnDefs: [
						{
							targets: [1],
							className:"name"
						},{
							targets: [4],
							className:"important"
						}
					],
		        	order: [[ 4, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#eraTop10').DataTable({
					data:data.eraTop10,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "rank" },
						{
							data: "name",
							render: function ( data, type, row ) {
								return '<img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
							}
						},
						{ data: "gameCount"  },
						{ data: "inning"  },
						{ data: "hit"  },
						{ data: "strikeOut"  },
						{ data: "fourBall"  },
						{ data: "runs"  },
						{ data: "complete"  },
						{ data: "shutout"  },
						{ data: "win"  },
						{ data: "lose"  },
						{ data: "save"  },
						{ data: "era",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
					],
					columnDefs: [
						{
							targets: [1],
							className:"name"
						},{
							targets: [13],
							className:"important"
						}
					],
		        	order: [[ 13, "asc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#winTop10').DataTable({
					data:data.winTop10,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "rank" },
						{
							data: "name",
							render: function ( data, type, row ) {
								return '<img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
							}
						},
						{ data: "gameCount"  },
						{ data: "inning"  },
						{ data: "hit"  },
						{ data: "strikeOut"  },
						{ data: "fourBall"  },
						{ data: "runs"  },
						{ data: "complete"  },
						{ data: "shutout"  },
						{ data: "win"  },
						{ data: "lose"  },
						{ data: "save"  },
						{ data: "era",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
					],
					columnDefs: [
						{
							targets: [1],
							className:"name"
						},{
							targets: [10],
							className:"important"
						}
					],
					//order: [[ 10, "asc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#saveTop10').DataTable({
					data:data.saveTop10,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "rank" },
						{
							data: "name",
							render: function ( data, type, row ) {
								return '<img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
							}
						},
						{ data: "gameCount"  },
						{ data: "inning"  },
						{ data: "hit"  },
						{ data: "strikeOut"  },
						{ data: "fourBall"  },
						{ data: "runs"  },
						{ data: "complete"  },
						{ data: "shutout"  },
						{ data: "win"  },
						{ data: "lose"  },
						{ data: "save"  },
						{ data: "era",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
					],
					columnDefs: [
						{
							targets: [1],
							className:"name"
						},{
							targets: [12],
							className:"important"
						}
					],
					//order: [[ 10, "asc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#strikeOutTop10').DataTable({
					data:data.strikeOutTop10,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{ data: "rank" },
						{
							data: "name",
							render: function ( data, type, row ) {
								return '<img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
							}
						},
						{ data: "gameCount"  },
						{ data: "inning"  },
						{ data: "hit"  },
						{ data: "strikeOut"  },
						{ data: "fourBall"  },
						{ data: "runs"  },
						{ data: "complete"  },
						{ data: "shutout"  },
						{ data: "win"  },
						{ data: "lose"  },
						{ data: "save"  },
						{ data: "era",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
					],
					columnDefs: [
						{
							targets: [1],
							className:"name"
						},{
							targets: [5],
							className:"important"
						}
					],
					//order: [[ 10, "asc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#pitchingAll').DataTable({
					data:data.pitchingResultList,
					searching: false,
					//ordering:false,
					paging: false,
					columns: [
						{ data: "rank" },
						{
							data: "name",
							render: function ( data, type, row ) {
								return '<a href="../../player/show/'+row.playerId+'" class="btn btn-default btn-xs"><img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data+'</a>';
							}
						},
						{ data: "gameCount"  },
						{ data: "inning"  },
						{ data: "hit"  },
						{ data: "strikeOut"  },
						{ data: "fourBall"  },
						{ data: "runs"  },
						{ data: "complete"  },
						{ data: "shutout"  },
						{ data: "win"  },
						{ data: "lose"  },
						{ data: "save"  },
						{ data: "era",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
						{ data: "whip",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
						{ data: "strikeAvg",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
						{ data: "runSupport",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
					],
					columnDefs: [
						{
							targets: [1],
							className:"name"
						},{
							//targets: [10],
							//className:"important"
						}
					],
					//order: [[ 10, "asc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#battingAll').DataTable({
					data:data.battingResultList,
					searching: false,
					ordering:true,
					paging: false,
					columns: [
						{
							data: "name",
							render: function ( data, type, row ) {
								return '<a href="../../player/show/'+row.playerId+'" class="btn btn-default btn-xs"><img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data+'</a>';
							}
						},
						{ data: "tpa"  },
						{ data: "atBats"  },
						{ data: "hit"  },
						{ data: "homerun"  },
						{ data: "rbi"  },
						{ data: "fourBall"  },
						{ data: "strikeOut"  },
						{ data: "twobase"  },
						{ data: "average",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "slg",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "ops",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "obp",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "notStrikeOut",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
						{ data: "rc27",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
						{ data: "avgHomerun",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )
						},
						{ data: "avgRbi",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
					],
					columnDefs: [
						{
							targets: [0],
							className:"name"
						},
					],
		        	order: [[ 9, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#leagueAll').DataTable({
					data:data.resultList,
					searching: false,
					ordering:true,
					paging: false,
					columns: [
					    { data:"rank" },
						{
							data: "teamName",
							render: function ( data, type, row ) {
								return '<img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
							}
						},
						{ data: "gameCount"  },
						{ data: "win"  },
						{ data: "lose"  },
						{ data: "draw"  },
						{ data: "percentage",
							render: $.fn.dataTable.render.number( ',', '.', 3, '' )
						},
						{ data: "points"  },
						{ data: "avg",
							render:function(data,type,row){
								if(data!=null){
									var value=data;
									return '<a href="../../team/batting/'+row.teamId+'/${totalLeagueId}">'+value.toFixed(3)+'</a>';
								}
							}
						},
						{ data: "era",
							render:function(data,type,row){
								if(data!=null){
									var value=data;
									return '<a href="../../team/pitching/'+row.teamId+'/${totalLeagueId}">'+value.toFixed(2)+'</a>';
								}
							}
						},
						<c:forEach var="resultList" items="${resultList}">
						{ data: "opponentMap.${resultList.teamId}"},
						</c:forEach>

					],
					columnDefs: [
						{
							targets: [1],
							className:"name"
						},
					],
		        	//order: [[ 9, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
				$('#nonTitle').DataTable({
					data:data.nonTitle,
					searching: false,
					ordering:false,
					paging: false,
					columns: [
						{
							data:"obpName",
							render: function ( data, type, row ) {
								var value=row.obp;
								if(row.obpName==null){
									return null;
								}
								return row.obpName+'/'+value.toFixed(3);
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								if ( rowData.obpRank==1 ) {
									$(td).addClass('important')
								}
							}
						},
						{
							data:"twobaseName",
							render: function ( data, type, row ) {
								if(row.twobaseName==null){
									return null;
								}
								return row.twobaseName+'/'+row.twobase+'本';
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								if ( rowData.twobaseRank==1 ) {
									$(td).addClass('important')
								}
							}
						},
						{
							data:"slgName",
							render: function ( data, type, row ) {
								var value=row.slg;
								if(row.slgName==null){
									return null;
								}
								return row.slgName+'/'+value.toFixed(3);
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								if ( rowData.slgRank==1 ) {
									$(td).addClass('important')
								}
							}
						},
						{
							data:"fourballName",
							render: function ( data, type, row ) {
								if(row.fourballName==null){
									return null;
								}
								return row.fourballName+'/'+row.fourball+'個';
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								if ( rowData.fourballRank==1 ) {
									$(td).addClass('important')
								}
							}
						},
						{
							data:"opsName",
							render: function ( data, type, row ) {
								var value=row.ops;
								if(row.opsName==null){
									return null;
								}
								return row.opsName+'/'+value.toFixed(3);
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								if ( rowData.opsRank==1 ) {
									$(td).addClass('important')
								}
							}
						},
						{
							data:"strikeoutName",
							render: function ( data, type, row ) {
								var value=row.strikeout;
								if(row.strikeoutName==null){
									return null;
								}
								return row.strikeoutName+'/'+value.toFixed(2);
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								if ( rowData.strikeoutRank==1 ) {
									$(td).addClass('important')
								}
							}
						},
						{
							data:"avgHRName",
							render: function ( data, type, row ) {
								var value=row.avgHR;
								if(row.avgHRName==null){
									return null;
								}
								return row.avgHRName+'/'+value.toFixed(2);
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								if ( rowData.avgHRRank==1 ) {
									$(td).addClass('important')
								}
							}
						},
						{
							data:"avgRbiName",
							render: function ( data, type, row ) {
								var value=row.avgRbi;
								if(row.avgRbiName==null){
									return null;
								}
								return row.avgRbiName+'/'+value.toFixed(3);
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								if ( rowData.avgRbiRank==1 ) {
									$(td).addClass('important')
								}
							}
						},
					],
					//columnDefs: [
					//	{
					//		targets: [1],
					//		className:"name"
					//	},
					//],
		        	//order: [[ 10, "desc" ]],
					lengthChange: false,
					info: false,
					responsive: true,
					processing: true,
				})
			})
			.error(function(){
				alert("サーバとの通信に失敗しました");
			})

			.always(function() {
				$.unblockUI();
			})
		});
	</script>
	<script>
	$(document).ready(function(){
		$(function(){
		    $.blockUI({
		      message: 'データの取得に時間がかかることがあります。しばらくお待ちください…',
		      css: {
		        border: 'none',
		        padding: '10px',
		        backgroundColor: '#333',
		        opacity: .5,
		        color: '#fff'
		      },
		      overlayCSS: {
		        backgroundColor: '#000',
		        opacity: 0.6
		      }
		    });
		    //setTimeout($.unblockUI, 500);
		});

	//});
	});
	</script>
	<meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
<!-- <ons-page>
<ons-scroller style="height: 200px; width:100%"> -->
<div id="contents">
<a href="${f:url('/result/old/')}${league.id}" target=_blank>・JSP版</a>
<a href="${f:url('/v2/#/result/season/')}${league.id}" target=_blank>・AngularJS版</a>
<b>・Ajax版</b>

<table border=1>
<tr>
	<td bgcolor="#006400" class=name><font size="+2" COLOR="#EEEEEE" >${league.title} チーム成績</font></td>
</tr>
<tr><td>
<table border=1 id="leagueAll">
	<thead>
	<tr>
		<th></th>
		<th>チーム名</th>
		<th>試合</th>
		<th>勝利</th>
		<th>敗北</th>
		<th>引き分け</th>
		<th>勝率</th>
		<th>勝ち点</th>
		<th>打率</th>
		<th>防御率</th>
		<c:forEach var="resultList" items="${resultList}">
		<th>対${resultList.shortName}</th>
		</c:forEach>
	</tr>
	</thead>
</table>
</td></tr>
</table>
<hr>
<table>
<tr>
	<td bgcolor="#006400" class=name><font size="+2" COLOR="#EEEEEE">打率TOP10(規定打席:<span id="regBats"></span>打席)</font></td>
</tr>
<tr>
	<td>
	<table id="averageTop10" border=1>
	<thead>
	<tr>
		<th></th>
		<th>選手名</th>
		<th>打席数</th>
		<th>打数</th>
		<th>安打</th>
		<th>HR</th>
		<th>打点</th>
		<th>四球</th>
		<th>三振</th>
		<th>二塁打</th>
		<th>打率</th>
	</tr>
	</thead>
	</table>
	</td>
</tr>
</table>
<table>
<tr>
	<td bgcolor="#006400" class=name><font size="+2" COLOR="#EEEEEE">HR TOP10</font></td>
</tr>
<tr>
	<td>
	<table id="HRTop10" border=1>
	<thead>
	<tr>
		<th></th>
		<th>選手名</th>
		<th>打席数</th>
		<th>打数</th>
		<th>安打</th>
		<th>HR</th>
		<th>打点</th>
		<th>四球</th>
		<th>三振</th>
		<th>二塁打</th>
		<th>打率</th>
	</tr>
	</thead>
	</table>
	</td>
</tr>
</table>
<table>
<tr>
	<td bgcolor="#006400" class=name><font size="+2" COLOR="#EEEEEE">打点TOP10</font></td>
</tr>
<tr>
	<td>
	<table id="RbiTop10" border=1>
	<thead>
	<tr>
		<th></th>
		<th>選手名</th>
		<th>打席数</th>
		<th>打数</th>
		<th>安打</th>
		<th>HR</th>
		<th>打点</th>
		<th>四球</th>
		<th>三振</th>
		<th>二塁打</th>
		<th>打率</th>
	</tr>
	</thead>
	</table>
	</td>
</tr>
</table>
<table>
<tr>
	<td bgcolor="#006400" class=name><font size="+2" COLOR="#EEEEEE">安打数TOP10</font></td>
</tr>
<tr>
	<td>
	<table id="hitTop10" border=1>
	<thead>
	<tr>
		<th></th>
		<th>選手名</th>
		<th>打席数</th>
		<th>打数</th>
		<th>安打</th>
		<th>HR</th>
		<th>打点</th>
		<th>四球</th>
		<th>三振</th>
		<th>二塁打</th>
		<th>打率</th>
	</tr>
	</thead>
	</table>
	</td>
</tr>
</table>
<table>
<tr>
	<td bgcolor="#006400" class=name><font size="+2" COLOR="#EEEEEE">防御率TOP10(規定投球回:<span id="regPitch"></span>回)</font></td>
</tr>
<tr>
	<td>
	<table id="eraTop10" border=1>
	<thead>
	<tr>
		<th></th>
		<th>選手名</th>
		<th>登板数</th>
		<th>投球回</th>
		<th>被安打</th>
		<th>奪三振</th>
		<th>与四球</th>
		<th>失点</th>
		<th>完投</th>
		<th>完封</th>
		<th>勝ち</th>
		<th>負け</th>
		<th>S</th>
		<th>防御率</th>
	</tr>
	</thead>
	</table>
	</td>
</tr>
</table>
<table>
<tr>
	<td bgcolor="#006400" class=name><font size="+2" COLOR="#EEEEEE">勝利数TOP10</font></td>
</tr>
<tr>
	<td>
	<table id="winTop10" border=1>
	<thead>
	<tr>
		<th></th>
		<th>選手名</th>
		<th>登板数</th>
		<th>投球回</th>
		<th>被安打</th>
		<th>奪三振</th>
		<th>与四球</th>
		<th>失点</th>
		<th>完投</th>
		<th>完封</th>
		<th>勝ち</th>
		<th>負け</th>
		<th>S</th>
		<th>防御率</th>
	</tr>
	</thead>
	</table>
	</td>
</tr>
</table>
<table>
<tr>
	<td bgcolor="#006400" class=name><font size="+2" COLOR="#EEEEEE">セーブ数TOP10</font></td>
</tr>
<tr>
	<td>
	<table id="saveTop10" border=1>
	<thead>
	<tr>
		<th></th>
		<th>選手名</th>
		<th>登板数</th>
		<th>投球回</th>
		<th>被安打</th>
		<th>奪三振</th>
		<th>与四球</th>
		<th>失点</th>
		<th>完投</th>
		<th>完封</th>
		<th>勝ち</th>
		<th>負け</th>
		<th>S</th>
		<th>防御率</th>
	</tr>
	</thead>
	</table>
	</td>
</tr>
</table>
<table>
<tr>
	<td bgcolor="#006400" class=name><font size="+2" COLOR="#EEEEEE">奪三振TOP10</font></td>
</tr>
<tr>
	<td>
	<table id="strikeOutTop10" border=1>
	<thead>
	<tr>
		<th></th>
		<th>選手名</th>
		<th>登板数</th>
		<th>投球回</th>
		<th>被安打</th>
		<th>奪三振</th>
		<th>与四球</th>
		<th>失点</th>
		<th>完投</th>
		<th>完封</th>
		<th>勝ち</th>
		<th>負け</th>
		<th>S</th>
		<th>防御率</th>
	</tr>
	</thead>
	</table>
	</td>
</tr>
</table>
<table>
	<tr>
		<td bgcolor="#006400" class=name><font size="+2" color="#EEEEEE">ノンタイトル部門</font></td>
	</tr>
	<tr>
		<td>
			<table border=1 id="nonTitle">
				<thead>
				<tr>
					<th bgcolor="#006400"><font color="#EEEEEE">出塁率(OBP)</font></th>
					<th bgcolor="#006400"><font color="#EEEEEE">最多二塁打</font></th>
					<th bgcolor="#006400"><font color="#EEEEEE">長打率(SLG)</font></th>
					<th bgcolor="#006400"><font color="#EEEEEE">最多四球</font></th>
					<th bgcolor="#006400"><font color="#EEEEEE">OPS</font></th>
					<th bgcolor="#006400"><font color="#EEEEEE">三振率(AB/K)</font></th>
					<th bgcolor="#006400"><font color="#EEEEEE">本塁打率<br>(AB/HR)</font></th>
					<th bgcolor="#006400"><font color="#EEEEEE">打点率<br>(RBI/AB)</font></th>
				</tr>
				</thead>
			</table>
		</td>
	</tr>
</table>
<hr>
<h2>打者全成績</h2>
<table id="battingAll" border=1>
	<thead>
	<tr>
		<th>選手名</th>
		<th>打席数</th>
		<th>打数</th>
		<th>安打</th>
		<th>HR</th>
		<th>打点</th>
		<th>四球</th>
		<th>三振</th>
		<th>二塁打</th>
		<th>打率</th>
		<th>長打率</th>
		<th>OPS</th>
		<th>出塁率</th>
		<th>三振率</th>
		<th>RC27</th>
		<th>本塁打率</th>
		<th>打点率</th>
	</tr>
	</thead>
</table>
<h2>投手全成績</h2>
<table id="pitchingAll" border=1>
	<thead>
	<tr>
		<th></th>
		<th>選手名</th>
		<th>登板数</th>
		<th>投球回</th>
		<th>被安打</th>
		<th>奪三振</th>
		<th>与四球</th>
		<th>失点</th>
		<th>完投</th>
		<th>完封</th>
		<th>勝ち</th>
		<th>負け</th>
		<th>S</th>
		<th>防御率</th>
		<th>WHIP</th>
		<th>奪三振率</th>
		<th>援護率</th>
	</tr>
	</thead>
	</table>
<hr>
<br><a href="${f:url('/statistics/') }">統計</a>
<br><a href="http://jcbldata.fc2web.com/cbl_stats.html">スタッツTOPへ</a>
<br><a href="http://jcbldata.fc2web.com/cbl_index.html" target="_top">HOME</a>
</div>
<!-- </ons-scroller>
</ons-page> -->
</body>
</html>