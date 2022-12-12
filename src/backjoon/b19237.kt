package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b19237 {
    data class Shark(
        var x: Int,
        var y: Int,
        var d: Int,
        var priorities: Array<IntArray>,
    )

    var N = 0
    var M = 0
    var K = 0
    val dx = arrayOf(-1, 1, 0, 0)
    val dy = arrayOf(0, 0, -1, 1)
    lateinit var board: Array<IntArray>
    lateinit var smellBoard: Array<Array<Pair<Int, Int>?>>
    lateinit var sharks: Array<Shark?>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        K = input[2].toInt()

        sharks = Array(M) { null }
        smellBoard = Array(N) { Array(N) { null } }

        board = (0 until N).map { i ->
            readLine().split(" ").mapIndexed { j, s ->
                val n = s.toInt() - 1
                if (n != -1) {
                    sharks[n] = Shark(i, j, 0, arrayOf())
                    smellBoard[i][j] = n to K
                }
                n }.toIntArray()
        }.toTypedArray()

        readLine().split(" ").mapIndexed { index, s -> sharks[index]!!.d = s.toInt() - 1 }

        sharks.map {  s ->
            s!!.priorities = (0 until 4).map { _ ->
                readLine().split(" ").map { it.toInt() - 1 }.toIntArray()
            }.toTypedArray()
        }

        var answer = 0

        while (answer++ < 1000) {
            sharksMove()
            setSmell(answer)
            if (sharks.count { it != null } == 1) break
        }

        println(if (answer > 1000) -1 else answer)
    }

    fun sharksMove() {
        for (i in 0 until M) {
            val s = sharks[i]
            if (s != null) sharkMove(s)
        }
    }

    fun sharkMove(shark: Shark) {
        val index = board[shark.x][shark.y]
        repeat(4) {
            val nd = shark.priorities[shark.d][it]
            val nx = shark.x + dx[nd]
            val ny = shark.y + dy[nd]

            if (nx in 0 until N && ny in 0 until N && smellBoard[nx][ny] == null) {
                val nextIndex = board[nx][ny]
                board[shark.x][shark.y] = -1
                if (nextIndex != -1 && nextIndex < index) sharks[index] = null
                else {
                    if (nextIndex != -1) sharks[nextIndex] = null
                    shark.d = nd
                    shark.x = nx
                    shark.y = ny
                    board[nx][ny] = index
                }
                return
            }
        }

        repeat(4) {
            val nd = shark.priorities[shark.d][it]
            val nx = shark.x + dx[nd]
            val ny = shark.y + dy[nd]
            if (nx in 0 until N && ny in 0 until N && smellBoard[nx][ny]?.first == index) {
                val nextIndex = board[nx][ny]
                board[shark.x][shark.y] = -1
                if (nextIndex != -1 && nextIndex < index) sharks[index] = null
                else {
                    if (nextIndex != -1) sharks[nextIndex] = null
                    shark.d = nd
                    shark.x = nx
                    shark.y = ny
                    board[nx][ny] = index
                }
                return
            }
        }
    }

    fun setSmell(t: Int) {
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (smellBoard[i][j] != null && smellBoard[i][j]!!.second <= t) smellBoard[i][j] = null
            }
        }

        val k = t + K
        sharks.forEachIndexed { index, shark ->
            if (shark != null) smellBoard[shark.x][shark.y] = index to k
        }
    }
}