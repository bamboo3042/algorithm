package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b19238 {
    var N = 0
    var M = 0
    var fuel = 0
    lateinit var board: Array<IntArray>
    val dx = arrayOf(-1, 0, 0, 1)
    val dy = arrayOf(0, -1, 1, 0)
    val passengerList: MutableList<Pair<Int, Int>> = mutableListOf()
    val destList: MutableList<Pair<Int, Int>> = mutableListOf()
    var carX = 0
    var carY = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        var input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        fuel = input[2].toInt()
        board = (0 until N).map {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }.toTypedArray()

        input = readLine().split(" ")
        carX = input[0].toInt() - 1
        carY = input[1].toInt() - 1

        repeat(M) {
            val (a, b, c, d) = readLine().split(" ").map { it.toInt() }
            passengerList.add(a - 1 to b - 1)
            destList.add(c - 1 to d - 1)
        }

        solve()

        println(fuel)
    }

    fun solve() {
        while (true) {
            if (passengerList.isEmpty()) return

            val (nextP, pd) = searchPassenger()
            if (nextP == -1 || pd > fuel) {
                fuel = -1
                return
            }

            carX = passengerList[nextP].first
            carY = passengerList[nextP].second
            fuel -= pd

            val d = searchDest(destList[nextP].first, destList[nextP].second)
            if (d == -1 || d > fuel) {
                fuel = -1
                return
            }

            carX = destList[nextP].first
            carY = destList[nextP].second
            fuel += d

            passengerList.removeAt(nextP)
            destList.removeAt(nextP)
        }
    }

    fun searchPassenger(): Pair<Int, Int> {
        val visited = Array(N) { BooleanArray(N) { false } }
        val queue = LinkedList<Triple<Int, Int, Int>> ()
        queue.add(Triple(carX, carY, 0))
        visited[carX][carY] = true

        var q = queue.peek()
        while (queue.isNotEmpty()) {
            q = queue.poll()
            val p = passengerList.indexOf(q.first to q.second)
            if (p != -1) break

            repeat(4) {
                val nx = q.first + dx[it]
                val ny = q.second + dy[it]
                if (nx in 0 until N && ny in 0 until N && !visited[nx][ny] && board[nx][ny] == 0) {
                    queue.add(Triple(nx, ny, q.third + 1))
                    visited[nx][ny] = true
                }
            }
        }
        while (queue.isNotEmpty() && queue.peek().third == q.third) {
            val tq = queue.poll()
            val p = passengerList.indexOf(tq.first to tq.second)
            if (p != -1) {
                if(tq.first < q.first) q = tq
                else if (q.first == tq.first && tq.second < q.second) q = tq
            }
        }
        val p = passengerList.indexOf(q.first to q.second)
        return if (p == -1) -1 to -1
        else p to q.third
    }

    fun searchDest(x: Int, y: Int): Int {
        val visited = Array(N) { BooleanArray(N) { false } }
        val queue = LinkedList<Triple<Int, Int, Int>> ()
        queue.add(Triple(carX, carY, 0))
        visited[carX][carY] = true

        while (queue.isNotEmpty()) {
            val q = queue.poll()

            repeat(4) {
                val nx = q.first + dx[it]
                val ny = q.second + dy[it]
                if (nx in 0 until N && ny in 0 until N && !visited[nx][ny] && board[nx][ny] == 0) {
                    if (nx == x && ny == y) return q.third + 1
                    if (q.third + 1 > fuel) return -1
                    queue.add(Triple(nx, ny, q.third + 1))
                    visited[nx][ny] = true
                }
            }
        }

        return -1
    }

    fun printBoard() {
        println("]-----] Board fuel: $fuel[-----[")
        val tBoard = Array(N) { CharArray(N) { '0' } }
        passengerList.map { tBoard[it.first][it.second] = 'P' }
        destList.map { tBoard[it.first][it.second] = 'D' }
        tBoard[carX][carY] = 'C'

        tBoard.map { l ->
            l.map { print("$it ") }
            println()
        }
        println()
    }
}