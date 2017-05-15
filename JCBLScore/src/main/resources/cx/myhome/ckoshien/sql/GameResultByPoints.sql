SELECT
  r.team_id,
  team_name,
  short_name,
  sum(win)+sum(lose)+sum(draw) as game_count,
  sum(win) as win,
  sum(lose)as lose,
  sum(draw)as draw,
  sum(win)/(sum(win)+sum(lose)) as percentage,
  sum(win)*4+sum(lose)*1+sum(draw)*2 as points,
  league_id,
  ba.avg,
  pa.era
 FROM result r
 inner join TEAM t on t.team_id=r.team_id
  left join(
  SELECT
     team_id,
     sum(hit)/sum(at_bats) as avg
  FROM batting_sum b
  inner join game g
  on b.game_id=g.game_id
  inner join league l
  on g.league_id=l.id
  where league_id=/*leagueId*/
  group by team_id
 ) as ba
on ba.team_id=r.team_id
left join (
  SELECT
    team_id,
    sum(runs)/sum(inning)*5 as era
  FROM pitching p
  inner join game g
  on p.game_id=g.game_id
  inner join league l
  on g.league_id=l.id
  where league_id=/*leagueId*/
  group by team_id
)pa
on pa.team_id=r.team_id
 WHERE league_id=/*leagueId*/ and jcbl_flg=0
 group by team_id
 having game_count</*regGameCount*/
 order by points desc;