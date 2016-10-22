select
 ms.id,
 ms.date,
CASE
  WHEN count is null THEN 0
  WHEN count is not null THEN count
END as count,
 weather,
 hightemp,
 lowtemp,
 img,
 regtime
 from m_schedule ms
left outer join
(SELECT
m.id,
date,
count(player_id) as count
FROM m_schedule m
left join t_schedule t on t.mst_id=m.id
where plans>=2
group by date) plan
on ms.date=plan.date
left outer join weather w
on w.date=ms.date
order by date