SELECT
  team_id,
  opponent,
  sum(win) as win,
  sum(lose) as lose,
  sum(draw) as draw,
  league_id
 FROM result
 WHERE league_id=/*leagueId*/
 group by team_id,opponent;