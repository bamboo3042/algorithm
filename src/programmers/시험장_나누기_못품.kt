package programmers

import java.util.PriorityQueue

//못품
class 시험장_나누기_못품 {
    class Solution {
        lateinit var dp: IntArray
        lateinit var num: IntArray
        lateinit var links: Array<IntArray>

        fun solution(k: Int, num: IntArray, links: Array<IntArray>): Int {
            dp = IntArray(num.size) {0}

            var top = 0

            for (i in num.indices) {
                dfs(i, num, links)
                if (dp[i] > dp[top]) top = i
            }

            val queue = PriorityQueue<Int> { o1, o2 ->
                if (dp[o1] > dp[o2]) -1
                else if (dp[o1] == dp[o2]) 0
                else 1
            }

            queue.add(top)

            repeat(k-1) {
                val node = queue.poll()

                val left = if (links[node][0] != -1) dp[links[node][0]] else 0
                val right = if (links[node][1] != -1) dp[links[node][1]] else 0

                if (left == 0 && right == 0) return  dp[node]
                if (left > right) {
                    queue.add(links[node][0])
                    links[node][0] = -1
                    dp[node] -= left
                }
                else {
                    queue.add(links[node][1])
                    links[node][1] = -1
                    dp[node] -= right
                }
                queue.add(node)
            }

            return dp[queue.poll()]
        }

        fun dfs(n: Int, num: IntArray, links: Array<IntArray>): Int {
            if (dp[n] != 0) return dp[n]

            val left = if (links[n][0] != -1) dfs(links[n][0], num, links) else 0
            val right = if (links[n][1] != -1) dfs(links[n][1], num, links) else 0

            dp[n] = num[n] + left + right

            return dp[n]
        }
    }
}