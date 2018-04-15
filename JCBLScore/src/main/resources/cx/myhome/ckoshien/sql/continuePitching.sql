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
  year_count as year
 FROM
 pitching p
  inner join GAME g on g.game_id=p.game_id
  inner join PLAYER pl on p.player_id=pl.id
  inner join TEAM t on t.team_id=pl.team_id
  inner join (
	select
		player_id,
		count(*) as year_count,
		max(year(game_date))as last_join_year

	from
		(select
 			player_id,
 			max(game_date)as game_date,
			team_id
		from
		pitching p
		INNER JOIN GAME g on p.game_id=g.game_id
		group by player_id,year(game_date)
 	)b2
 group by player_id
 having year_count>=2 and last_join_year>=/*lastYear*/
 )b3
 on b3.player_id=p.PLAYER_ID
 group by team_id,player_id