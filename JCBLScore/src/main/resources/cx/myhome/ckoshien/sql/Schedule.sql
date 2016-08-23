SELECT
 date,
 count(p.id),
 name,
 plans,
 sc_password,
 te.team_id,
 team_name
 FROM m_schedule m
LEFT OUTER JOIN t_schedule t ON m.id=t.mst_id
LEFT OUTER JOIN player p ON p.id=t.player_id
LEFT OUTER JOIN team te ON te.team_id=p.team_id
where plans>=2
group by date,team_id