SELECT
  p.player_id,
  name,
  pl.team_id as team_id,
  team_name,
  -- count(*) as game_count,
  sum(runs)/sum(inning)*5 as era,
  (sum(hit)+sum(four_ball))/sum(inning) as WHIP,
  sum(strike_out)/sum(inning)*5 as strike_avg,
  sum(inning) as inning,
  sum(pa) as pa,
  sum(hit) as hit,
  sum(homerun) as homerun,
  sum(four_ball) as four_ball,
  sum(strike_out) as strike_out,
  sum(runs) as runs,
  sum(complete) as complete,
  sum(shutout) as shutout,
  sum(win) as win,
  sum(lose) as lose,
  sum(save) as save,
  p3.run_support
 FROM
 pitching p
  inner join GAME g on g.game_id=p.game_id
  inner join PLAYER pl on p.player_id=pl.id
  inner join TEAM t on t.team_id=pl.team_id
  left outer join(
  	SELECT
		p.player_id as player_id,
		sum(p2.runs)/sum(p2.inning)*5 as run_support
	FROM pitching p
	inner join GAME g
	on p.game_id=g.game_id
	inner join pitching p2
	on p.game_id=p2.game_id and p.myteam_id!=p2.myteam_id
	where game_date>=/*beginDate*/ and game_date<=/*endDate*/
	group by player_id)p3
	on p3.player_id=p.PLAYER_ID
WHERE game_date>=/*beginDate*/ and game_date<=/*endDate*/
group by player_id
order by /*$order*/;