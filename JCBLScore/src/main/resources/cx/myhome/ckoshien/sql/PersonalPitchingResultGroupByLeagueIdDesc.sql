SELECT
  name,
  p.team_id as team_id,
  player_id,
  t.short_name as short_name,
  -- count(*) as game_count,
  sum(runs)/sum(inning)*5 as era,
  (sum(hit)+sum(four_ball))/sum(inning) as WHIP,
  sum(strike_out)/sum(inning)*5 as strike_avg,
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
on pc.team_id=t.team_id
inner join league l
on g.league_id=l.id
where player_id=/*playerId*/
group by league_id
order by league_id desc;