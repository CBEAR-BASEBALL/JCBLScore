SELECT
  player_id,
  name,
  sum(win) as win,
  sum(lose) as lose,
  sum(save) as save
 FROM
 pitching p
  inner join GAME g on g.game_id=p.game_id
  inner join PLAYER pl on p.player_id=pl.id
  WHERE
  player_id=/*playerId*/
  and ((game_date>=/*beginDate*/ and game_date</*todayDate*/)
  or (game_date=/*todayDate*/ and game_number<=/*gameNumber*/))
group by player_id;