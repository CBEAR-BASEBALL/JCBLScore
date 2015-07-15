SELECT
  name,
  p.team_id as team_id,
  player_id,
  t.short_name as opponent_name,
  sum(runs)/sum(inning)*5 as era,
  sum(inning) as inning,
  sum(hit) as hit,
  sum(four_ball) as four_ball,
  sum(strike_out) as strike_out,
  sum(runs) as runs,
  sum(complete) as complete,
  sum(shutout) as shutout,
  sum(pc.win) as win,
  sum(pc.lose) as lose,
  sum(pc.save) as save,
  g.game_date as game_date,
  g.game_id as game_id,
  g.game_number as game_number,
  myteam_id,
  g.league_id as league_id,
  l.short_title as title
 FROM pitching pc
inner join GAME g
on pc.game_id=g.game_id
inner join RESULT r
on pc.myteam_id=r.team_id and g.game_id=r.game_id
inner join player p
on p.id=pc.player_id
inner join team t
on r.opponent=t.team_id
inner join league l
on g.league_id=l.id
where p.team_id=/*teamId*/ and game_date>=/*beginDate*/ and game_date<=/*endDate*/
group by player_id,league_id,game_date,game_number with rollup