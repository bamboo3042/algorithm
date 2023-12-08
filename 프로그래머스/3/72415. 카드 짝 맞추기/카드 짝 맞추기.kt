class Solution {

    val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
    var answer = Int.MAX_VALUE
    val cards: Array<Array<Pair<Int, Int>>> = Array(7) { arrayOf((0 to 0), (0 to 0)) }
    val cardCheck = BooleanArray(7) { true }
    var cardCount = 0
    lateinit var board: Array<IntArray>

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        this.board = board.copyOf()
        board.forEachIndexed { x, ints ->
            ints.forEachIndexed { y, i ->
                if (i != 0) {
                    if (cardCheck[i]) {
                        cardCheck[i] = false
                        cardCount++
                        cards[i][0] = x to y
                    }
                    else cards[i][1] = x to y
                }
            }
        }

        dfs(0, 0, r, c)

        return answer + 2 * cardCount
    }

    fun findDistance(r: Int, c: Int, tr: Int, tc: Int): Int {
        val visited = Array(4) { BooleanArray(4) {false} }
        val queue = ArrayDeque<Triple<Int, Int, Int>> ()
        visited[r][c] = true
        queue.add(Triple(0, r, c))

        while (queue.isNotEmpty()) {
            val (d, x, y) = queue.removeFirst()

            if (x == tr && y ==tc) return d

            for ((dx, dy) in directions) {
                var nx = x + dx
                var ny = y + dy

                if (nx in 0 until 4 && ny in 0 until 4 && !visited[nx][ny]) {
                    visited[nx][ny] = true
                    queue.addLast(Triple(d+1, nx, ny))
                }

                while (nx in 0 until 4 && ny in 0 until 4) {
                    if ((board[nx][ny] != 0 || (dx != 0 && (nx == 0 ||  nx == 3)) || (dy != 0 && (ny == 0 ||  ny == 3)))) {
                        if (!visited[nx][ny]) {
                            visited[nx][ny] = true
                            queue.addLast(Triple(d + 1, nx, ny))
                        }

                        break
                    }
                    nx += dx
                    ny += dy
                }
            }
        }

        return 0
    }

    fun dfs(count: Int, d: Int, r: Int, c: Int) {
        if (count == cardCount) {
            answer = minOf(answer, d)
            return
        }

        for (i in 1 .. 6) {
            if (!cardCheck[i]) {
                val d1 = findDistance(r, c, cards[i][0].first, cards[i][0].second) + findDistance(cards[i][0].first, cards[i][0].second, cards[i][1].first, cards[i][1].second)
                val d2 = findDistance(r, c, cards[i][1].first, cards[i][1].second) + findDistance(cards[i][1].first, cards[i][1].second, cards[i][0].first, cards[i][0].second)

                cardCheck[i] = true
                board[cards[i][0].first][cards[i][0].second] = 0
                board[cards[i][1].first][cards[i][1].second] = 0
                if (d1 < d2) dfs(count+1, d+d1, cards[i][1].first, cards[i][1].second)
                else dfs(count+1, d+d2, cards[i][0].first, cards[i][0].second)
                board[cards[i][0].first][cards[i][0].second] = i
                board[cards[i][1].first][cards[i][1].second] = i
                cardCheck[i] = false
            }
        }
    }
}