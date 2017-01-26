select
  *
from player p
inner join (
SELECT
 p.id
 FROM m_schedule m
inner JOIN t_schedule t ON m.id=t.mst_id
inner JOIN player p ON p.id=t.player_id
inner JOIN team te ON te.team_id=p.team_id
group by player_id
) m
on m.id=p.id
order by team_id