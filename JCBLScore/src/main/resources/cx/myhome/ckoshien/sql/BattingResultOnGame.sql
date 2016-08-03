/**
 * 第○試合終了時の成績を知るSQL
 */
SELECT
  player_id,
  name,
  sum(hit)/sum(at_bats) as average,
  sum(at_bats),
  sum(hit),
  sum(rbi),
  sum(homerun)
 FROM
 batting_sum b
  inner join GAME g on g.game_id=b.game_id
  inner join PLAYER pl on b.player_id=pl.id
  WHERE
  ((game_date>='2016-01-01' and game_date<'2016-06-05')
  or (game_date='2016-06-05' and game_number<=1))
group by player_id;