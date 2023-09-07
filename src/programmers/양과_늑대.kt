package programmers

class 양과_늑대 {
    class Solution {

        lateinit var visited: BooleanArray
        lateinit var board: Array<IntArray>
        lateinit var info: IntArray
        var answer = 1

        fun solution(info: IntArray, edges: Array<IntArray>): Int {
            this.info = info
            visited = BooleanArray(1.shl(info.size))
            board = Array(info.size) { IntArray(2) {-1} }
            edges.forEach { (p, c) ->
                if (board[p][0] == -1) board[p][0] = c
                else board[p][1] = c
            }

            search(1)

            return answer
        }

        fun search(n: Int) {
            if (visited[n]) return

            visited[n] = true

            var sheep = 0
            var wolf = 0
            for (i in info.indices) {
                if (n and 1.shl(i) != 0) {
                    if (info[i] == 0) sheep++
                    else wolf++
                }
            }

            if (sheep <= wolf) return

            answer = maxOf(answer, sheep)

            for (i in info.indices) {
                if (n and 1.shl(i) == 0) continue

                for (next in board[i]) if (next != -1) search(n or 1.shl(i))
            }
        }
    }
}