package programmers

class 퍼즐_조각_채우기 {
    class Solution {
        val dx = intArrayOf(1, -1, 0, 0)
        val dy = intArrayOf(0, 0, 1, -1)
        val piece = mutableMapOf<Int, MutableList<Array<IntArray>>> ()
        lateinit var visited: Array<BooleanArray>
        var answer = 0

        fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
            setTable(table)
            visited = Array(game_board.size) { BooleanArray(game_board[0].size) {false} }

            for (i in game_board.indices) {
                for (j in game_board[0].indices) {
                    if (game_board[i][j] == 0 && !visited[i][j]) {
                        val (size, tat) = findTat(i, j, game_board)

                        if (piece[size] != null) {
                            for (t in piece[size]!!) {
                                if (match(tat, t, size)) break
                            }
                        }
                    }
                }
            }

            return answer
        }

        fun setTable(table: Array<IntArray>) {
            visited = Array(table.size) { BooleanArray(table[0].size) {false} }
            for (i in table.indices) {
                for (j in table[0].indices) {
                    if (table[i][j] == 1 && !visited[i][j]) {
                        val (size, tat) = findTat(i, j, table)

                        piece[size]?.add(tat) ?: piece.put(size, mutableListOf(tat))
                    }
                }
            }
        }

        fun findTat(sx: Int, sy: Int, board: Array<IntArray>): Pair<Int, Array<IntArray>> {
            val queue = ArrayDeque<Pair<Int, Int>>()
            val temp = mutableListOf<Pair<Int, Int>>()
            var minX = Int.MAX_VALUE
            var minY = Int.MAX_VALUE
            var maxX = Int.MIN_VALUE
            var maxY = Int.MIN_VALUE

            queue.add(sx to sy)
            visited[sx][sy] = true

            while (queue.isNotEmpty()) {
                val (x, y) = queue.removeFirst()

                temp.add(x to y)
                minX = minOf(minX, x)
                minY = minOf(minY, y)
                maxX = maxOf(maxX, x)
                maxY = maxOf(maxY, y)

                repeat(4) {
                    val nx = x + dx[it]
                    val ny = y + dy[it]

                    if (nx in board.indices && ny in board[0].indices && board[nx][ny] == board[sx][sy] && !visited[nx][ny]) {
                        visited[nx][ny] = true
                        queue.add(nx to ny)
                    }
                }
            }

            val tat = Array(maxX - minX + 1) { IntArray(maxY - minY + 1) {0} }
            temp.forEach { (x, y) -> tat[x - minX][y - minY] = 1 }
            return temp.size to tat
        }

        fun rotate(tat: Array<IntArray>): Array<IntArray> {
            val temp = Array(tat[0].size) { IntArray(tat.size) {0} }

            for (i in tat.indices) {
                for (j in tat[0].indices) {
                    temp[tat[0].size-j-1][i] = tat[i][j]
                }
            }

            return temp
        }

        fun match(tat1: Array<IntArray>, tat2: Array<IntArray>, size: Int): Boolean {
            var temp = tat2
            repeat(4) {
                if (tat1.contentDeepEquals(temp)) {
                    answer += size
                    piece[size]!!.remove(tat2)
                    return true
                }
                temp = rotate(temp)
            }
            return false
        }
    }
}