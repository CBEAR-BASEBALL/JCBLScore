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
order by last_join_date desc) team_date
on p.team_id=team_date.team_id;