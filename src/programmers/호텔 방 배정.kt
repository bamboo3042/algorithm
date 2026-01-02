package programmers

class `호텔 방 배정` {
    class Solution {
        fun solution(k: Long, roomNumber: LongArray): LongArray {
            val map = mutableMapOf<Long, Long>()

            return roomNumber.map { rn ->
                if (map.contains(rn)) {
                    var temp = map[rn]!!
                    val visited = mutableListOf(rn)

                    while (map.contains(temp)) {
                        visited.add(temp)
                        temp = map[temp]!!
                    }

                    val next = temp + 1
                    map[temp] = next
                    visited.forEach { n -> map[n] = next }
                    temp
                }
                else {
                    map[rn] = rn + 1

                    rn
                }
            }.toLongArray()
        }
    }
}