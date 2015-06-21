SELECT
  r.team_id,
  team_name,
  sum(win)+sum(lose)+sum(draw) as game_count,
  sum(win) as win,
  sum(lose)as lose,
  sum(draw)as draw,
  sum(win)/(sum(win)+sum(lose)) as percentage,
  sum(win)*4+sum(lose)*1+sum(draw)*2 as points,
  league_id
 FROM result r
 inner join TEAM t on t.team_id=r.team_id
 WHERE league_id=/*leagueId*/
 group by team_id
 order by percentage desc,points desc;
