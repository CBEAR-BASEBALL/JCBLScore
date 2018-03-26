select
t2.team_id,
t2.team_name,
t2.short_name,
t2.last_join_date,
p.NAME,
p.ID
from
(SELECT
  t.team_id as team_id,
  t.team_name as team_name,
  t.short_name as short_name,
   max(game_date) as last_join_date
 FROM team t
  left outer join result r
  on t.team_id=r.team_id
  left outer join game g
  on r.game_id=g.game_id
group by team_id
order by jcbl_flg asc,last_join_date desc)t2
inner join player p
on t2.team_id=p.TEAM_ID