class Solution {
    val dx = listOf(1, 0, -1, 0)
    val dy = listOf(0, 1, 0, -1)
    lateinit var minCost: Array<Array<IntArray>>

    fun solution(board: Array<IntArray>): Int {
        minCost = Array(board.size) { Array(board.size) { IntArray(4) { Int.MAX_VALUE } } }

        dfs(board, 0, 0, 0, 2)
        dfs(board, 0, 0, 0, 3)

        return minCost.last().last().minOrNull()!!
    }

    fun dfs(board: Array<IntArray>, x: Int, y: Int, cost: Int, di: Int) {
        if (minCost[x][y][di] <= cost) return
        if (x == board.size - 1 && y == board.size - 1) {
            minCost[x][y][di] = cost
            return
        }

        minCost[x][y][di] = cost

        repeat(4) {
            if (it == di) return@repeat

            val nx = x + dx[it]
            val ny = y + dy[it]
            val nc = if ((it + di) % 2 == 0) 100
            else 600

            if (nx in board.indices && ny in board.indices && board[nx][ny] == 0) {
                dfs(board, nx, ny, cost + nc, (it + 2) % 4)
            }
        }
    }
}