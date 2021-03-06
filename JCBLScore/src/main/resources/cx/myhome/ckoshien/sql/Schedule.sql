SELECT
 date,
 p.id as player_id,
 name,
 case
 when plans=0 then '×'
 when plans=1 then '△'
 when plans=2 then '○'
 end as plans,
 sc_password,
 te.team_id as team_id,
 team_name,
 mst_id
 FROM m_schedule m
LEFT OUTER JOIN t_schedule t ON m.id=t.mst_id
LEFT OUTER JOIN player p ON p.id=t.player_id
LEFT OUTER JOIN team te ON te.team_id=p.team_id
order by player_id,date