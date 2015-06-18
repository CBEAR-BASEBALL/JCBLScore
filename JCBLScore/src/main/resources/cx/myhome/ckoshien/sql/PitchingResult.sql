SELECT
  player_id,
  name,
  count(*) as game_count,
  sum(runs)/sum(inning)*5 as era,
  (sum(hit)+sum(four_ball))/sum(inning) as WHIP,
  sum(strike_out)/sum(inning)*5 as strike_avg,
  sum(inning) as inning,
  sum(pa) as pa,
  sum(hit) as hit,
  sum(homerun) as homerun,
  sum(four_ball) as four_ball,
  sum(strike_out) as strike_out,
  sum(runs) as runs,
  sum(complete) as complete,
  sum(shutout) as shutout,
  sum(win) as win,
  sum(lose) as lose,
  sum(save) as save
 FROM (
  select
    player_id,
    pl.name,
    runs,
    inning,
    hit,
    four_ball,
    strike_out,
    pa,
    homerun,
    complete,
    shutout,
    win,
    save,
    lose
   from
 pitching p
  inner join GAME g on g.game_id=p.game_id
  inner join PLAYER pl on p.player_id=pl.id
  WHERE game_date>=/*beginDate*/ and game_date<=/*endDate*/
 ) as pitchig_result
  group by player_id
 order by era;