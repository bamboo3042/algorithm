package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b3197 {
    var R = 0
    var C = 0
    lateinit var board: Array<CharArray>
    var groupOut = mutableListOf<Pair<Int, Int>>()
    val dx = arrayOf(0, 0, 1, -1)
    val dy = arrayOf(1, -1, 0, 0)
    val ducks = mutableListOf<Pair<Int, Int>>()
    lateinit var groupBoard: Array<IntArray>
    val group = mutableListOf<Int>()
    val rank = mutableListOf<Int>()

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        R = input[0]
        C = input[1]
        board = Array(R) { readLine().toCharArray() }
        groupBoard = Array(R) { IntArray(C) {-1} }
        init()
        var answer = 0
        while (!endCheck()) {
            answer++
            melt()
            groupCheck()
        }

        println(answer)
    }

    fun init() {
        for (i in 0 until R) {
            for (j in 0 until C) {
                if (board[i][j] == 'L') ducks.add(i to j)
                if (groupBoard[i][j] == -1 && board[i][j] != 'X') {
                    val queue = ArrayDeque<Pair<Int, Int>> ()
                    val color = group.size
                    group.add(color)
                    rank.add(0)
                    groupBoard[i][j] = color
                    queue.add(i to j)

                    while (queue.isNotEmpty()) {
                        val (x, y) = queue.removeFirst()
                        var check = false

                        repeat(4) {
                            val nx = x + dx[it]
                            val ny = y + dy[it]

                            if (nx in 0 until R && ny in 0 until C) {
                                if (board[nx][ny] == 'X') check = true
                                else if (groupBoard[nx][ny] == -1){
                                    groupBoard[nx][ny] = color
                                    queue.add(nx to ny)
                                }
                            }
                        }
                        if (check) groupOut.add(x to y)
                    }
                }
            }
        }
    }

    fun melt() {
        val newGroup = mutableListOf<Pair<Int, Int>>()
        for ((x, y) in groupOut) {
            val color = groupBoard[x][y]
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx in 0 until R && ny in 0 until C && groupBoard[nx][ny] == -1) {
                    groupBoard[nx][ny] = color
                    newGroup.add(nx to ny)
                }
            }
        }
        groupOut = newGroup
    }

    fun groupCheck() {
        for ((x, y) in  groupOut) {
            val color = groupBoard[x][y]
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx in 0 until R && ny in 0 until C && groupBoard[nx][ny] != -1 && groupBoard[nx][ny] != color) { union(color, groupBoard[nx][ny])
                }
            }
        }
    }

    fun find(n: Int): Int {
        if (group[n] == n) return n
        group[n] = find(group[n])
        return group[n]
    }

    fun union(n: Int, m: Int) {
        val x = find(n)
        val y = find(m)

        if (x == y) return

        if (rank[x] < rank[y]) {
            group[x] = y
        } else {
            group[y] = x

            if (rank[x] == rank[y]) rank[x]++
        }
    }

    fun endCheck(): Boolean {
        val color1 = groupBoard[ducks[0].first][ducks[0].second]
        val color2 = groupBoard[ducks[1].first][ducks[1].second]
        return find(color1) == find(color2)
    }
}