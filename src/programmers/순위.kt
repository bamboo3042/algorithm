package programmers

class 순위 {
    class Solution {

        val win = mutableMapOf<Int, MutableSet<Int>>()
        val lose = mutableMapOf<Int, MutableSet<Int>>()
        var N = 0
        var answer = 0

        fun solution(n: Int, results: Array<IntArray>): Int {
            N = n
            repeat(n) {
                win[it] = mutableSetOf()
                lose[it] = mutableSetOf()
            }
            results.forEach { (a, b) ->
                win[a-1]!!.add(b-1)
                lose[b-1]!!.add(a-1)
            }

            repeat(n) {checkMember(it)}

            return answer
        }

        fun checkMember(n: Int) {
            val visited = BooleanArray(N) {false}
            val queue = ArrayDeque<Pair<Int, Boolean>> ()
            win[n]?.forEach {
                visited[it] = true
                queue.add(it to true)
            }
            lose[n]?.forEach {
                visited[it] = true
                queue.add(it to false)
            }
            visited[n] = true

            while (queue.isNotEmpty()) {
                val (temp, isWin) = queue.removeFirst()

                for (i in 0 until N) {
                    if (!visited[i]) {
                        if (isWin) {
                            win[temp]!!.forEach { next ->
                                if (!visited[next]) {
                                    visited[next] = true
                                    win[n]!!.add(next)
                                    queue.add(next to isWin)
                                }
                            }
                        }
                        else {
                            lose[temp]!!.forEach { next ->
                                if (!visited[next]) {
                                    visited[next] = true
                                    lose[n]!!.add(next)
                                    queue.add(next to isWin)
                                }
                            }
                        }
                    }
                }
            }

            if (!visited.contains(false)) answer++
        }
    }
}