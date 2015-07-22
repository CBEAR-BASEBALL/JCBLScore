SELECT
  name,
  p.team_id as team_id,
  player_id,
  short_name,
  sum(tpa) as tpa,
  sum(at_bats)as at_bats,
  sum(hit) as hit,
  (sum(hit)/sum(at_bats)) as average,
  ((sum(hit)+sum(twobase)*1+sum(homerun)*2)/sum(at_bats)) as SLG,
	((sum(hit)+sum(four_ball))/sum(tpa))+((sum(hit)+sum(twobase)*1+sum(homerun)*2)/sum(at_bats)) as OPS,
	(sum(hit)+sum(four_ball))/sum(tpa) as OBP,
	CASE
    WHEN sum(at_bats)/sum(strike_out) is null THEN 0
    WHEN sum(at_bats)/sum(strike_out) is not null THEN sum(at_bats)/sum(strike_out)
  	END as not_strike_out,
  sum(rbi) as rbi,
  sum(four_ball) as four_ball,
  sum(strike_out) as strike_out,
  sum(twobase) as twobase,
  sum(homerun) as homerun,
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
on b.team_id=t.team_id
inner join league l
on g.league_id=l.id
where player_id=/*playerId*/
group by league_id with rollup