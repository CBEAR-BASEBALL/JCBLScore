SELECT
  player_id,
  name,
  sum(homerun) as homerun
 FROM
 batting_sum b
  inner join GAME g on g.game_id=b.game_id
  inner join PLAYER pl on b.player_id=pl.id
  WHERE
  player_id in
 (
  SELECT
   player_id
    FROM batting_sum b
  inner join GAME g on g.game_id=b.game_id
  WHERE b.game_id=/*gameId*/ and b.homerun>=1
 )
 and
  ((game_date>=/*beginDate*/ and game_date</*todayDate*/)
  or (game_date=/*todayDate*/ and game_number<=/*gameNumber*/))
group by player_id