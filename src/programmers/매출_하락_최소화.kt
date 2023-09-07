package programmers

class 매출_하락_최소화 {
    class Solution {

        lateinit var dp: Array<IntArray>
        lateinit var team: MutableMap<Int, MutableList<Int>>

        fun solution(sales: IntArray, links: Array<IntArray>): Int {
            dp = Array(sales.size) { IntArray(2) {Int.MAX_VALUE} }
            team = mutableMapOf()
            sales.forEachIndexed { index, i ->
                dp[index][1] = i
                dp[index][0] = 0
            }
            links.map { (a, b) -> team[a-1]?.add(b-1) ?: team.put(a-1, mutableListOf(b-1)) }

            dfs(0)

            return minOf(dp[0][0], dp[0][1])
        }

        fun dfs(n: Int) {
            if (team[n] == null) return

            var sum = 0
            var check = false
            var min = Int.MAX_VALUE
            for (i in team[n]!!) {
                dfs(i)
                sum += minOf(dp[i][0], dp[i][1])
                check = check or (dp[i][0] > dp[i][1])
                min = minOf(min, dp[i][1] - dp[i][0])
            }

            dp[n][1] += sum
            if (check) dp[n][0] = sum
            else dp[n][0] = sum + min
        }
    }
}