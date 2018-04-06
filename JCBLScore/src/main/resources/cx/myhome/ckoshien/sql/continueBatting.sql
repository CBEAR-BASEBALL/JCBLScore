SELECT
  name,
  p.team_id as team_id,
  b.player_id,
  team_name,
  sum(tpa) as tpa,
  sum(at_bats)as at_bats,
  sum(hit) as hit,
  (sum(hit)/sum(at_bats)) as average,
  ((sum(hit)+sum(twobase)*1+sum(homerun)*2)/sum(at_bats)) as SLG,
	((sum(hit)+sum(four_ball))/sum(tpa))+((sum(hit)+sum(twobase)*1+sum(homerun)*2)/sum(at_bats)) as OPS,
	(sum(hit)+sum(four_ball))/sum(tpa) as OBP,
	((((sum(hit)+sum(twobase)*1+sum(homerun)*2)+0.26*sum(four_ball)-0.03*sum(strike_out)+3*sum(tpa))*(sum(hit)+sum(four_ball)+2.4*sum(tpa)))/(9*sum(tpa))-0.9*sum(tpa))*27/(sum(at_bats)-sum(hit)) as RC27,
	CASE
    WHEN sum(at_bats)/sum(strike_out) is null THEN 0
    WHEN sum(at_bats)/sum(strike_out) is not null THEN sum(at_bats)/sum(strike_out)
  	END as not_strike_out,
  	CASE
    WHEN sum(at_bats)/sum(homerun) is null THEN 0
    WHEN sum(at_bats)/sum(homerun) is not null THEN sum(at_bats)/sum(homerun)
    END as avg_homerun,
    CASE
    WHEN sum(rbi)/sum(at_bats) is null THEN 0
    WHEN sum(rbi)/sum(at_bats) is not null THEN sum(rbi)/sum(at_bats)
  END as avg_rbi,
  sum(rbi) as rbi,
  sum(four_ball) as four_ball,
  sum(strike_out) as strike_out,
  sum(twobase) as twobase,
  sum(homerun) as homerun,
  myteam_id,
  g.league_id as league_id,
  -- year(game_date) as year,
  year_count as year
 FROM batting_sum b
inner join GAME g
on b.game_id=g.game_id
inner join RESULT r
on b.myteam_id=r.team_id and g.game_id=r.game_id
inner join player p
on p.id=b.player_id
inner join team t
on b.team_id=t.team_id
inner join (
	select
		player_id,
		count(*) as year_count,
		max(year(game_date))as last_join_year
	from
		(select
 			player_id,
 			max(game_date)as game_date
		from
		batting_sum b
		INNER JOIN GAME g on b.game_id=g.game_id
		group by player_id,year(game_date)
	)b2
group by player_id
having count(*)>=2 and last_join_year>=/*lastYear*/)b3
on b3.player_id=b.PLAYER_ID
group by team_id,player_id