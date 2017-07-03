/**各シーズンのアクティブプレーヤーの数
SELECT
count(player_id) as member_count,
league_id,
short_title
FROM
(SELECT DISTINCT
	player_id,
	name,
  league_id,
  short_title
 FROM batting_sum b
  INNER JOIN GAME g on b.game_id=g.game_id
  INNER JOIN PLAYER p on b.player_id=p.id
  INNER JOIN league l on g.league_id=l.id)t
group by league_id
**/
/**各シーズンのチーム別アクティブプレイヤー数**/
SELECT
count(player_id) as member_count,
league_id,
short_title as short_name,
team_id
FROM
(SELECT DISTINCT
	player_id,
	name,
  league_id,
  short_title,
  p.team_id
 FROM batting_sum b
  INNER JOIN GAME g on b.game_id=g.game_id
  INNER JOIN PLAYER p on b.player_id=p.id
  INNER JOIN league l on g.league_id=l.id
  INNER JOIN team t on b.team_id=t.team_id)t
group by league_id,team_id
order by team_id,league_id