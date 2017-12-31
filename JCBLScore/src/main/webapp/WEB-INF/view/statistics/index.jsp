<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<script type="text/javascript" src="${f:url('/js/jquery-latest.js') }"></script>
	<script type="text/javascript" src="${f:url('/js/highcharts.src.js') }"></script>
	<script type="text/javascript" src="${f:url('/js/highcharts-more.src.js') }"></script>
<script>
$(function () {
  $('#rep').highcharts({
    chart: {
      type: 'column',
      //width: '600',
      //height: '400'
    },

    title: {
      text: 'シーズン・チームごとの参加者数推移'
    },

    xAxis: {
      title: {
        text: 'シーズン'
      },
      categories: [
		<c:forEach var="i" items="${seasonList}">
		"${i}",
		</c:forEach>
		]
    },

    yAxis: {
      min: 0,
      title: {
        text: '参加者数'
      },
      allowDecimals: false,
      stackLabels: {
        enabled: true,
        x: -1,
        style: {
          fontSize: '16px',
          fontWeight: 'bold',
          color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
        }
      }
    },

    legend: {
      backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
      borderColor: '#CCC',
      borderWidth: 1,
      shadow: false,
      itemHoverStyle: {
        cursor: 'default'
      },
    },
    tooltip: {
		shared: false,
		pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y}</b><br/>',
		backgroundColor: '#FFFFFF',
		style: {
			color: '#000000'
		}
	},
    plotOptions: {

      column: {
        stacking: 'normal',
        events: {
          legendItemClick: function () {
            return false;
          }
        }
      }
    },

    series: [
		<c:forEach var="k" items="${teamList}">
		{
			name: '${k.teamName}',
			data: [
			<c:forEach var="i" items="${countDtos}">
			<c:choose>
			<c:when test="${k.teamId==i.teamId}">
				${i.memberCount},
			</c:when>
			</c:choose>
			</c:forEach>
			],
		},
		</c:forEach>

	],

    credits: {
      enabled: false
    }
  });
});
  </script>
  <script>
$(function () {
  $('#year').highcharts({
    chart: {
      type: 'column',
      //width: '600',
      //height: '400'
    },

    title: {
      text: '年・チームごとの参加者数推移'
    },

    xAxis: {
      title: {
        text: 'シーズン'
      },
      categories: [
		<c:forEach var="i" items="${yearList}">
		"${i}",
		</c:forEach>
		]
    },

    yAxis: {
      min: 0,
      title: {
        text: '参加者数'
      },
      allowDecimals: false,
      stackLabels: {
        enabled: true,
        x: -1,
        style: {
          fontSize: '16px',
          fontWeight: 'bold',
          color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
        }
      }
    },

    legend: {
      backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
      borderColor: '#CCC',
      borderWidth: 1,
      shadow: false,
      itemHoverStyle: {
        cursor: 'default'
      },
    },
    tooltip: {
		shared: false,
		pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y}</b><br/>',
		backgroundColor: '#FFFFFF',
		style: {
			color: '#000000'
		}
	},
    plotOptions: {

      column: {
        stacking: 'normal',
        events: {
          legendItemClick: function () {
            return false;
          }
        }
      }
    },

    series: [
		<c:forEach var="k" items="${teamList}">
		{
			name: '${k.teamName}',
			data: [
			<c:forEach var="i" items="${yearDtos}">
			<c:choose>
			<c:when test="${k.teamId==i.teamId}">
				${i.memberCount},
			</c:when>
			</c:choose>
			</c:forEach>
			],
		},
		</c:forEach>

	],

    credits: {
      enabled: false
    }
  });
});
  </script>
</head>
<body>
	<div id="rep"></div>
	<div id="year"></div>
	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<hr>
<!-- 1 -->
<ins class="adsbygoogle"
     style="display:block"
     data-ad-client="ca-pub-6455792541973521"
     data-ad-slot="1903562094"
     data-ad-format="auto"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>
</body>
</html>