/*打席数-打数＝四球でない場合のデータを表示する*/
SELECT
	player_id,
	name,
  game_date,
  game_number,
  tpa,
  at_bats,
  four_ball
 FROM batting_sum b
  INNER JOIN GAME g on b.game_id=g.game_id
  INNER JOIN PLAYER p on b.player_id=p.id
  WHERE tpa-at_bats<>four_ball