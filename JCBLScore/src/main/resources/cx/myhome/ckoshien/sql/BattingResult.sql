SELECT
	count(*) as game_count,
	player_id,
	name,
	(sum(hit)/sum(at_bats)) as average,
	((sum(hit)+sum(twobase)*1+sum(homerun)*2)/sum(at_bats)) as SLG,
	(sum(hit)/sum(at_bats))+((sum(hit)+sum(twobase)*1+sum(homerun)*2)/sum(at_bats)) as OPS,
 	 (sum(hit)+sum(four_ball))/sum(tpa) as OBP,
	sum(strike_out)/sum(tpa) as not_strike_out,
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
  WHERE game_date>=/*beginDate*/ and game_date<=/*endDate*/
group by player_id
order by /*$order*/;