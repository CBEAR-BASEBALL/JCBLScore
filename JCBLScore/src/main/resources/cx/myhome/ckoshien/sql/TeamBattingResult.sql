SELECT
  name,
  p3.team_id as team_id,
--  t.team_id,
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
inner join team t
on r.opponent=t.team_id
inner join league l
on g.league_id=l.id
inner join
(select
p2.ID,
p2.NAME,
CASE
	WHEN t3.TEAM_ID is null THEN t4.TEAM_ID
	ELSE t3.TEAM_ID
END as TEAM_ID,
CASE
	WHEN t3.TEAM_ID is null THEN t4.TEAM_NAME
	ELSE t3.TEAM_NAME
END as TEAM_NAME,
CASE
	WHEN t3.TEAM_ID is null THEN t4.SHORT_NAME
	ELSE t3.SHORT_NAME
END as SHORT_NAME
from
player p
left outer join (
	select
	th.PLAYER_ID,
	th.TEAM_ID
	from team_history th
	inner join league l1
	on l1.ID=th.START_LEAGUE_ID
	left outer join league l2
	on l2.ID=th.END_LEAGUE_ID
	where l1.BEGIN_DATE<=/*beginDate*/
	and (l2.END_DATE>=/*endDate*/ or l2.END_DATE is null)
) t2
on t2.PLAYER_ID=p.ID
inner join team t3
on t3.TEAM_ID=t2.TEAM_ID
right outer join player p2
on p2.ID=p.ID
right outer join team t4
on t4.TEAM_ID=p2.TEAM_ID
where p2.ID is not null) p3
on p3.ID=b.PLAYER_ID
where p3.team_id=/*teamId*/ and game_date>=/*beginDate*/ and game_date<=/*endDate*/
group by player_id,league_id,game_date,game_number with rollup