package programmers

import java.util.PriorityQueue

class 상담원_인원 {
    class Solution {
        lateinit var dp: Array<IntArray>
        lateinit var consultant: IntArray
        var answer = Int.MAX_VALUE
        var K = 0
        var N = 0

        fun solution(k: Int, n: Int, reqs: Array<IntArray>): Int {
            K = k
            N = n
            dp = Array(k+1) { IntArray(n-k+2) {0} }
            val newReqs = Array(n+1) { reqs.filter { req -> req[2] == it } }

            for (taskNum in 1 .. k) {
                for (count in 1 .. n-k+1) {
                    val work = PriorityQueue<Int>()

                    newReqs[taskNum].forEach { task ->
                        while (work.isNotEmpty() && work.peek() <= task[0]) work.poll()

                        if (work.size < count) work.add(task[0] + task[1])
                        else {
                            val endTime = work.poll()
                            dp[taskNum][count] += endTime - task[0]
                            work.add(endTime + task[1])
                        }
                    }
                }
            }

            consultant = IntArray(k+1) {0}
            dfs(1, n)

            return answer
        }
        fun dfs(k: Int, n: Int) {
            if (k == K+1) {
                if (consultant.sum() == N)answer = minOf(answer, consultant.mapIndexed { index, i -> dp[index][i] }.sum())
            }
            else {
                for (i in 1 .. n) {
                    consultant[k] = i
                    dfs(k+1, n-i)
                }
            }
        }
    }
}