/*
 * HRや打点の累積グラフを出力するときに使えるSQL
 * */
SELECT
game_id,
(SELECT
  sum(homerun) FROM batting_sum  as b
 where b.game_id<=a.game_id
 and player_id='1'
 and b.game_id>='147'
),
(SELECT
  sum(homerun) FROM batting_sum  as b
 where b.game_id<=a.game_id
 and player_id='22'
 and b.game_id>='147'
)
from batting_sum as a
where game_id>='147'
group by game_id;