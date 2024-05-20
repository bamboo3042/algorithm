select
count(info.id) as FISH_COUNT,
max(info.LENGTH) as MAX_LENGTH,
info.FISH_TYPE as FISH_TYPE
from FISH_INFO as info
join (
    select 
    ID, 
    FISH_TYPE, 
    IFNULL(LENGTH, 10) as LENGTH,
    TIME
    from FISH_INFO
) as temp
on info.ID = temp.ID
group by info.FISH_TYPE
having avg(temp.LENGTH) >= 33
order by 3