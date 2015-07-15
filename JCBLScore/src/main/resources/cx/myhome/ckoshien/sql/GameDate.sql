SELECT
  game_id,
  league_id,
  game_date,
  title
FROM game g
inner join league l
on g.league_id=l.id
group by game_date
order by game_date desc;