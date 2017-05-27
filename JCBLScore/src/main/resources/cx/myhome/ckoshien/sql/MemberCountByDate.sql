SELECT
count(player_id) as member_count,
league_id,
short_title,
game_date
FROM
(SELECT DISTINCT
	player_id,
	name,
  league_id,
  short_title,
  game_date
 FROM batting_sum b
  INNER JOIN GAME g on b.game_id=g.game_id
  INNER JOIN PLAYER p on b.player_id=p.id
  INNER JOIN league l on g.league_id=l.id)t
group by game_date