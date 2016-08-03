SELECT
  name,
  p.team_id as team_id,
  player_id,
  t.short_name as opponent_name,
  sum(tpa) as tpa,
  sum(at_bats)as at_bats,
  sum(hit) as hit,
  (sum(hit)/sum(at_bats)) as average,
  sum(rbi) as rbi,
  sum(four_ball) as four_ball,
  sum(strike_out) as strike_out,
  sum(twobase) as twobase,
  sum(homerun) as homerun,
  g.game_date as game_date,
  g.game_id as game_id,
  g.game_number as game_number,
  myteam_id,
  g.league_id as league_id,
  l.short_title as title
 FROM batting_sum b
inner join GAME g
on b.game_id=g.game_id
inner join RESULT r
on b.myteam_id=r.team_id and g.game_id=r.game_id
inner join player p
on p.id=b.player_id
inner join team t
on r.opponent=t.team_id
inner join league l
on g.league_id=l.id
where b.player_id=/*playerId*/
group by player_id,league_id,game_date,game_number with rollup
;