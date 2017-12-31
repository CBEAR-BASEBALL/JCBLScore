SELECT
count(player_id) as member_count,
team_id,
year as short_name
FROM
(SELECT DISTINCT
	player_id,
	name,
  year(game_date) as year,
  p.team_id
 FROM batting_sum b
  INNER JOIN GAME g on b.game_id=g.game_id
  INNER JOIN PLAYER p on b.player_id=p.id
  INNER JOIN league l on g.league_id=l.id
  INNER JOIN team t on b.team_id=t.team_id)t
group by year,team_id