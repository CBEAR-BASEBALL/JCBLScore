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
	<script src="//cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$(function(){
			$.blockUI({
				//message: $('#tallContent'),
				message: 'しばらくお待ちください…',
				css: {
					border: 'none',
					padding: '15px',
					backgroundColor: '#fff',
					opacity: .6,
					color: '#333'
				},
				overlayCSS: {
					backgroundColor: '#000',
					opacity: 0.6
				}
			});
		    //setTimeout($.unblockUI, 500);
		});
			//$.fn.dataTable.ext.errMode = 'none';
			$.extend( $.fn.dataTable.defaults, {
	        language: {
	            url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
	        }
	    	})
			$.ajax('/JCBLScore/api/v1/result/continuation/',
			{
				type: 'get',
				dataType: 'json'
			})

			.success(function(data){
				//$('span#regBats').text(data.regAtBats);
				//$('span#regPitch').text(data.regAtPitch);
				$('#pitchingAll').DataTable({
					data:data.pitchingResultList,
					searching: true,
					ordering:true,
					//displayLength:30,
					paging: false,
					columns: [
						{ data: "rank" },
						{
							data: "name",
							render: function ( data, type, row ) {
								if(row.year!=null){
									return '<a href="../../player/show/'+row.playerId+'" class="btn btn-default btn-xs"><img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data+'</a>';
								}else if(row.year==null && row.playerId==null){
									return null;
								}else {
									return '<a href="../../player/show/'+row.playerId+'" class="btn btn-default btn-xs"><img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'"><b>'+data+'(通算)'+'</b></a>';
								}
							}
						},
						{ data: "year"  },
						{ data: "inning",
							render: $.fn.dataTable.render.number( ',', '.', 2, '' )},
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
					],
					columnDefs: [
						{
							targets: [0],
							className:"name"
						},{
							//targets: [10],
							//className:"important"
						}
					],
					//order: [[ 2, "desc" ]],
					lengthChange: false,
					//info: false,
					responsive: true,
					processing: true,
				})
				$('#battingAll').DataTable({
					data:data.battingResultList,
					searching: true,
					//displayLength:30,
					ordering:true,
					paging: false,
					columns: [
						{
							data: "name",
							render: function ( data, type, row ) {
								if(row.year!=null){
									return '<a href="../../player/show/'+row.playerId+'" class="btn btn-default btn-xs"><img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data+'</a>';
								}else if(row.year==null && row.playerId==null){
									return null;
								}else {
									return '<a href="../../player/show/'+row.playerId+'" class="btn btn-default btn-xs"><img width="15" height="15" src="../../img/'+row.teamId+'.jpg" title="'+row.teamName+'"><b>'+data+'(通算)'+'</b></a>';
								}
							}
						},
						{ data: "year"},
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
						{ data: "teamId"},
					],
					columnDefs: [
						{
							targets: [0],
							className:"name"
						},
						{
							targets: [18],
							visible:false
						},
					],
		        	order: [[ 18, "asc" ]],
					lengthChange: false,
					//info: true,
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
</head>
<html>
<body>
<h2>JCBL継続参加選手打撃通算成績</h2>
<p>昨年度参加しており、かつ2年以上打撃成績がある選手です。</p>
<table id="battingAll" border=1>
	<thead>
	<tr>
		<th>選手名</th>
		<th>　</th>
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
	<tfoot>
	<tr>
		<th>選手名</th>
		<th>　</th>
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
	</tfoot>
</table>
<h2>JCBL継続参加選手投球通算成績</h2>
<p>昨年度参加しており、かつ2年以上投球成績がある選手です。</p>
<table id="pitchingAll" border=1>
	<thead>
	<tr>
		<th></th>
		<th>選手名</th>
		<th></th>
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
	</tr>
	</thead>
	<tfoot>
	<tr>
		<th></th>
		<th>選手名</th>
		<th></th>
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
	</tr>
	</tfoot>
	</table>
</body>
</html>