select
player_id,
name,
league_id,
case
	when event_type=1 then '打率'
	when event_type=2 then 'HR'
	when event_type=3 then '打点'
	when event_type=4 then '安打数'
	when event_type=5 then '防御率'
	when event_type=6 then '勝利'
	when event_type=7 then 'セーブ'
	when event_type=8 then '奪三振'
end event_type_str,
event_type,
value,
l.TOTAL_FLG,
l.SHORT_TITLE
from title_holder t
inner join player p
on p.ID=t.PLAYER_ID
inner join league l
on l.ID=t.LEAGUE_ID
order by league_id desc,event_type