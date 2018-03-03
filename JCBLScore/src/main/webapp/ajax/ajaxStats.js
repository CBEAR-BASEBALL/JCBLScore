$(document).ready(function() {
	//$.fn.dataTable.ext.errMode = 'none';
		$.extend( $.fn.dataTable.defaults, {
        language: {
            url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
        }
    	})
		$.ajax('/JCBLScore/api/v1/result/season/38',
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
							return '<img width="15" height="15" src="../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
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
							return '<img width="15" height="15" src="../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
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
							return '<img width="15" height="15" src="../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
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
							return '<img width="15" height="15" src="../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
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
							return '<img width="15" height="15" src="../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
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
							return '<img width="15" height="15" src="../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
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
							return '<img width="15" height="15" src="../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
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
							return '<img width="15" height="15" src="../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
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
							return '<img width="15" height="15" src="../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
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
							return '<img width="15" height="15" src="../img/'+row.teamId+'.jpg" title="'+row.teamName+'">'+data;
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


		})

		.always(function() {
			$.unblockUI();
		})
	});