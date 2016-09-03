SELECT
 p.id,
 name,
 te.team_id,
 team_name
 FROM m_schedule m
inner JOIN t_schedule t ON m.id=t.mst_id
inner JOIN player p ON p.id=t.player_id
inner JOIN team te ON te.team_id=p.team_id
group by player_id