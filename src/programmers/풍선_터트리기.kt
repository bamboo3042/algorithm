package programmers

class 풍선_터트리기 {
    class Solution {
        fun solution(a: IntArray): Int {
            val dp = IntArray(a.size) {0}
            var min = a[0]
            for (i in 1 until a.size) {
                if (a[i] > min) dp[i]++
                else min = a[i]
            }
            min = a.last()
            for (i in a.size-2 downTo 0) {
                if (a[i] > min) dp[i]++
                else min = a[i]
            }
            return dp.count {it <= 1}
        }
    }
}