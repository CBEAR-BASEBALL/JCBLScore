select
  th.ID,
  player_id,
  name,
  th.TEAM_ID,
  th.START_LEAGUE_ID,
  th.END_LEAGUE_ID,
  l.SHORT_TITLE as start_title,
  l2.SHORT_TITLE as end_title,
  team_name
from player p
inner join team_history th on th.PLAYER_ID=p.ID
inner join team t on t.TEAM_ID=th.TEAM_ID
right outer join league l on l.ID=th.START_LEAGUE_ID
left outer join league l2 on l2.ID=th.end_LEAGUE_ID
where p.ID=/*playerId*/
order by th.START_LEAGUE_ID desc