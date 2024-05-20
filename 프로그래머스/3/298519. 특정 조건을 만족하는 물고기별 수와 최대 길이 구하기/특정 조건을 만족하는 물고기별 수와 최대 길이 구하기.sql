select
count(*) as FISH_COUNT,
max(IFNULL(LENGTH,10)) as MAX_LENGTH,
FISH_TYPE as FISH_TYPE
from FISH_INFO
group by FISH_TYPE
having avg(IFNULL(LENGTH,10)) >= 33
order by 3