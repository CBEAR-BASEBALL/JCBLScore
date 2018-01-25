select
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
	*
	from team_history th
	where th.START_LEAGUE_ID<=/*leagueId*/
	and (th.END_LEAGUE_ID>=/*leagueId*/ or th.END_LEAGUE_ID is null)
) t2
on t2.PLAYER_ID=p.ID
inner join team t3
on t3.TEAM_ID=t2.TEAM_ID
right outer join player p2
on p2.ID=p.ID
right outer join team t4
on t4.TEAM_ID=p2.TEAM_ID
where p2.ID is not null
order by team_id,id