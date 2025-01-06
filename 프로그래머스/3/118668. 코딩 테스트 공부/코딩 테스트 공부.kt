class Solution {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        var maxAlp = 0
        var maxCop = 0

        problems.forEach { (tAlp, tCop) ->
            maxAlp = maxOf(tAlp, maxAlp)
            maxCop = maxOf(tCop, maxCop)
        }

        if (alp >= maxAlp && cop >= maxCop) return 0

        val dp = Array(maxAlp + 1) { IntArray(maxCop + 1) { 1000000 } }
        val newProblems = problems + arrayOf(intArrayOf(0, 0, 1, 0, 1), intArrayOf(0, 0, 0, 1, 1))

        val startAlp = minOf(alp, maxAlp)
        val startCop = minOf(cop, maxCop)

        dp[startAlp][startCop] = 0

        for (tAlp in startAlp .. maxAlp) {
            for (tCop in startCop .. maxCop) {
                newProblems.forEach { (alpReq, copReq, alpRwd, copRwd, cost) ->
                    if (tAlp >= alpReq && tCop >= copReq) {
                        val nAlp = minOf(tAlp + alpRwd, maxAlp)
                        val nCop = minOf(tCop + copRwd, maxCop)

                        dp[nAlp][nCop] = minOf(dp[nAlp][nCop], dp[tAlp][tCop] + cost)
                    }
                }
            }
        }

        return dp[maxAlp][maxCop]
    }
}