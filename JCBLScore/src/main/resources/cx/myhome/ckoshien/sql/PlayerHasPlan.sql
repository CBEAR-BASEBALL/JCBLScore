select
  *
from player p
inner join
(SELECT
  t.team_id as team_id,
  t.team_name as team_name,
  t.short_name as short_name,
   max(game_date) as last_join_date
 FROM team t
  left outer join result r on t.team_id=r.team_id
  left outer join game g on r.game_id=g.game_id
  group by team_id
order by jcbl_flg asc,last_join_date desc) team_date
on p.team_id=team_date.team_id
where p.id in (
SELECT
 p.id
 FROM m_schedule m
inner JOIN t_schedule t ON m.id=t.mst_id
inner JOIN player p ON p.id=t.player_id
inner JOIN team te ON te.team_id=p.team_id
group by player_id
)