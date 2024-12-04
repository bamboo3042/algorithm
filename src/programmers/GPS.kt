package programmers

class GPS {
    class Solution {
        fun solution(n: Int, m: Int, edgeList: Array<IntArray>, k: Int, gpsLog: IntArray): Int {
            val board = Array(n+1) { mutableListOf(it) }

            edgeList.forEach { (a, b) ->
                board[a].add(b)
                board[b].add(a)
            }

            val dp = Array(n+1) { IntArray(n+1) { Int.MAX_VALUE } }
            dp[gpsLog.first()][gpsLog.first()] = 0

            for (time in 1 until k) {
                for (prev in 0 .. n) {
                    if (dp[time-1][prev] == Int.MAX_VALUE) continue

                    for (next in board[prev]) {
                        if (next == gpsLog[time]) dp[time][next] = minOf(dp[time][next], dp[time-1][prev])
                        else dp[time][next] = minOf(dp[time][next], dp[time-1][prev] + 1)
                    }
                }


            }

            return if (dp[k-1][gpsLog.last()] == Int.MAX_VALUE) -1 else dp[k-1][gpsLog.last()]
        }
    }
}