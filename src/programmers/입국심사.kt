package programmers

class 입국심사 {
    class Solution {
        fun solution(n: Int, times: IntArray): Long {
            var l = 0L
            var r = 1000000000L * 1000000000L

            while (l < r) {
                val m = (l + r).shr(1)
                val t = times.sumOf { m / it }
                if (t >= n) r = m
                else l = m + 1
            }

            return r
        }
    }
}