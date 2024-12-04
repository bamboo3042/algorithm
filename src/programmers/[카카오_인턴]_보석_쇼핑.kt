package programmers

class `카카오_인턴_보석_쇼핑` {
    class Solution {
        fun solution(gems: Array<String>): IntArray {
            val answer = intArrayOf(1, gems.size)
            var start = 0
            var end = 0
            val gemCount = gems.toSet().associateWith { 0 }.toMutableMap()
            val set = mutableSetOf<String>()

            while (end < gems.size) {
                while (end < gems.size && set.size != gemCount.keys.size) {
                    gemCount[gems[end]] = gemCount[gems[end]]!! + 1
                    set.add(gems[end])
                    end++
                }

                while (start < gems.size && gemCount[gems[start]] != 1) {
                    gemCount[gems[start]] = gemCount[gems[start]]!! - 1
                    start++
                }

                val last = minOf(end, gems.size-1)

                if (last - start < answer[1] - answer[0]) {
                    answer[0] = start+1
                    answer[1] = last+1
                }

                gemCount[gems[start]] = 0
                set.remove(gems[start])
                start++
            }

            return answer
        }
    }
}