<%@page pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8"/>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css"/>
<style>
/* カレンダーのstyleを整える*/
.ui-datepicker td span, .ui-datepicker td a {
    text-align: center;
}
.ui-datepicker select.ui-datepicker-year, .ui-datepicker select.ui-datepicker-month {
    width: auto;
}
.ui-datepicker select.ui-datepicker-month {
    margin-left: 1em;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="http://g.0-oo.net/gcalendar-holidays.js"></script>
<script>
var current_year = null; // 表示されている年
var current_month = null; // 表示されている月
var selected_date = []; // 選択した年月日
var holidays;
$(function(){
	GCalHolidays.get(show, undefined, undefined);

});
function show(holidays) {
	datepick(holidays);
}
function datepick(holidays){
	var months = ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"];
    $('#datepicker').datepicker({
    	beforeShowDay: function(date){
    	if(date.getDay()==0){
    		return [true,"gcal-sunday",""]
    	}else if(date.getDay()==6){
    		return [true,"gcal-saturday",""]
    	}else{
    		for(var j=0;j<holidays.length;j++){
    			var h=holidays[j];
    			if(date.getFullYear()==h.year && date.getMonth()+1 == h.month && date.getDate()==h.date){
    				return [true,"gcal-holiday",h.title]
    			}
    		}
    		return [true,"",""]
    	}
    },
	prevText: '前月',
    nextText: '次月',
	yearSuffix: "年",
	monthNames: months,
    monthNamesShort: months,
	showMonthAfterYear: true,
	dayNamesMin: ["日", "月", "火", "水", "木", "金", "土"],
	dateFormat: 'yy-mm-dd',
	onSelect: function( select_date ){
            // selected_dateに入ってない場合は入れる。入ってる場合は削除。
            var index=$.inArray(select_date,selected_date)
            if ( index == -1 ) {
                selected_date.push(select_date)
                selected_date=$.grep(selected_date, function(e){return e;});
                $("[name=date]").val(selected_date);
            }
            else {
                delete selected_date[index];
                selected_date=$.grep(selected_date, function(e){return e;});
                $("[name=date]").val(selected_date);
            }

            setTimeout( function(){
                for ( i in selected_date) {
                    var data = selected_date[i].split('-');
                    var year = data[0];
                    // 年が違う
                    if ( year != current_year ){ continue }
                    var month = data[1];
                    // 月が違う
                    if ( month != current_month ) { continue }
                    var day = data[2] * 1;

                    // 日を探して色をつける
                    $('.ui-datepicker-calendar a').each(function(){
                        var $this = $(this);
                        if ( $this.text() == day ) {
                            $this.parent('td').css('background-color','#F00');
                        }
                    });
                }
            }, 100);
        },
        onChangeMonthYear: function( year, month ) {
        	current_year = year;
            current_month = month;

            setTimeout( function(){
                for ( i in selected_date ) {
                    var data = i.split('-');
                    var year = data[0];
                    if ( year != current_year ){ continue }
                    var month = data[1];
                    if ( month != current_month ) { continue }
                    var day = data[2] * 1;

                    $('.ui-datepicker-calendar a').each(function(){
                        var $this = $(this);
                        if ( $this.text() == day ) {
                            $this.parent('td').css('background-color','#F00');
                        }
                    });
                }
            }, 100);
        }
    });

    // current_year,current_monthを初期化
    var current = $.datepicker.formatDate('yy-mm-dd', $('#datepicker').datepicker('getDate') );
    if ( !current_year ) {
       var data = current.split('-');
        current_year = data[0];
        current_month = data[1] * 1;
    }
}
</script>
</head>
<body>
<div id="datepicker"></div><br/>
<s:form>
	<html:text property="date" disabled="true" />
	<html:hidden property="date" />
	<s:submit property="createComplete" value="登録"/>
</s:form>

</body>
</html>