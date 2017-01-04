SELECT
	count(*) as game_count,
	player_id,
	name,
	p.team_id as team_id,
	team_name,
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
  	sum(tpa) as tpa,
	sum(at_bats) as at_bats,
	sum(hit) as hit,
	sum(rbi) as rbi,
	sum(four_ball) as four_ball,
	sum(strike_out) as strike_out,
	sum(twobase) as twobase,
	sum(homerun) as homerun
 FROM batting_sum b
  INNER JOIN GAME g on b.game_id=g.game_id
  INNER JOIN PLAYER p on b.player_id=p.id
  INNER JOIN TEAM t on t.team_id=p.team_id
  WHERE game_date>=/*beginDate*/ and game_date<=/*endDate*/
group by player_id
order by /*$order*/;