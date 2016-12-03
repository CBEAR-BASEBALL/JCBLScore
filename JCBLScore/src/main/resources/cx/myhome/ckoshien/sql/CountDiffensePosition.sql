SELECT
case position
  WHEN '1'THEN '投手'
  WHEN '2'THEN '捕手'
  WHEN '3'THEN '一塁手'
  WHEN '4'THEN '二塁手'
  WHEN '5'THEN '外野手'
  WHEN '6'THEN 'DH'
END as pos,
count(*) as count
 FROM batting_sum
where player_id=/*playerId*/ and position>0
group by position