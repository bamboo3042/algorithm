package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.absoluteValue

class b23289 {
    var N = 0
    var M = 0
    var K = 0
    lateinit var board: Array<IntArray>
    var answer = 0
    val dx = arrayOf(0, 0, -1, 1)
    val dy = arrayOf(1, -1, 0, 0)
    val spread = arrayOf(
        arrayOf(
            -1 to 0,
            0 to 1,
            1 to 0,
            0 to 1
        ),
        arrayOf(
            -1 to 0,
            0 to -1,
            1 to 0,
            0 to -1
        ),
        arrayOf(
            0 to -1,
            -1 to 0,
            0 to 1,
            -1 to 0
        ),
        arrayOf(
            0 to -1,
            1 to 0,
            0 to 1,
            1 to 0
        )
    )
    lateinit var heaters: MutableList<Triple<Int, Int, Int>>
    lateinit var checks: MutableList<Pair<Int, Int>>
    lateinit var boardTemp: Array<IntArray>
    lateinit var walls: Array<Array<BooleanArray>>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        K = input[2].toInt()
        board = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        boardTemp = Array(N) { IntArray(M) {0} }
        heaters = mutableListOf()
        checks = mutableListOf()
        repeat(N) { i ->
            repeat(M) { j ->
                if (board[i][j] == 5) checks.add(i to j)
                else if (board[i][j] > 0) heaters.add(Triple(i, j, board[i][j] - 1))
            }
        }
        walls = Array(N) { Array(M) { booleanArrayOf(false, false) } }
        repeat(readLine().toInt()) {
            val (x, y, d) = readLine().split(" ").map { it.toInt() }
            walls[x-1][y-1][d] = true
        }

        while (!checkBoard() && answer < 101) {
            wind()
            controlTemp()
            downTemp()
            answer++
        }
        println(answer)
    }

    fun wind() {
        var visited = Array(N) { BooleanArray(M) {false} }
        val spreadWind = fun(list: List<Pair<Int, Int>>, d: Int, n: Int): List<Pair<Int, Int>> {
            val tempList = mutableListOf<Pair<Int, Int>>()
            list.forEach { (x, y) ->
                boardTemp[x][y] += n
                var tx = x
                var ty = y
                for (i in 0 until 2) {
                    val nx = tx + spread[d][i].first
                    val ny = ty + spread[d][i].second
                    if (nx !in 0 until N || ny !in 0 until M || !wallCheck(tx, ty, nx, ny)) break
                    tx = nx
                    ty = ny
                }
                if ((tx-x).absoluteValue + (ty-y).absoluteValue == 2 && !visited[tx][ty]) {
                    visited[tx][ty] = true
                    tempList.add(tx to ty)
                }

                tx = x + dx[d]
                ty = y + dy[d]
                if (tx in 0 until N && ty in 0 until M && !visited[tx][ty] && wallCheck(x, y, tx, ty)) {
                    visited[tx][ty] = true
                    tempList.add(tx to ty)
                }

                tx = x
                ty = y
                for (i in 2 until 4) {
                    val nx = tx + spread[d][i].first
                    val ny = ty + spread[d][i].second
                    if (nx !in 0 until N || ny !in 0 until M || !wallCheck(tx, ty, nx, ny)) break
                    tx = nx
                    ty = ny
                }
                if ((tx-x).absoluteValue + (ty-y).absoluteValue == 2 && !visited[tx][ty]) {
                    visited[tx][ty] = true
                    tempList.add(tx to ty)
                }

            }

            return tempList
        }

        heaters.forEach { (x, y, d) ->
            var checkList = listOf(x+dx[d] to y+dy[d])
            visited = Array(N) { BooleanArray(M) {false} }
            for (i in 5 downTo 1) { checkList = spreadWind(checkList, d, i) }
        }
    }

    fun wallCheck(x: Int, y: Int, nx: Int, ny: Int) = (x < nx && !walls[nx][y][0]) || (nx < x && !walls[x][y][0]) || (ny > y && !walls[x][y][1]) || (ny < y && !walls[x][ny][1])

    fun controlTemp() {
        val newTempBoard = Array(N) { boardTemp[it].clone() }
        for (i in 0 until N) {
            for (j in 0 until M) {
                repeat(4) {
                    val nx = i + dx[it]
                    val ny = j + dy[it]
                    if (nx in 0 until N && ny in 0 until M && boardTemp[i][j] > boardTemp[nx][ny] && wallCheck(i, j, nx, ny)) {
                        val t = (boardTemp[i][j] - boardTemp[nx][ny])/4
                        newTempBoard[i][j] -= t
                        newTempBoard[nx][ny] += t
                    }
                }
            }
        }
        boardTemp = newTempBoard
    }

    fun downTemp() {
        for (i in 0 until N) {
            if (boardTemp[i][0] > 0) boardTemp[i][0]--
            if (boardTemp[i][M-1] > 0) boardTemp[i][M-1]--
        }
        for (j in 1 until M-1) {
            if (boardTemp[0][j] > 0) boardTemp[0][j]--
            if (boardTemp[N-1][j] > 0) boardTemp[N-1][j]--
        }
    }

    fun checkBoard(): Boolean {
        checks.forEach { (x, y) ->
            if (boardTemp[x][y] < K) return false
        }
        return true
    }

    fun printBoard() {
        println("]-----] BoardTemp [-----[")
        boardTemp.forEach { l ->
            l.forEach { print("$it ") }
            println()
        }
        println()
    }
}