package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b23288 {
    var N = 0
    var M = 0
    var K = 0
    lateinit var board: Array<IntArray>
    var answer = 0
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(1, 0, -1, 0)
    var x = 0
    var y = 0
    var d = 0
    lateinit var dice: List<Int>


    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        K = input[2].toInt()
        dice = List(6) { it+1 }
        board = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }

        repeat(K) { diceMove() }
        println(answer)
    }

    fun diceMove() {
        val newDice = MutableList(6) { 0 }
        var nx = x + dx[d]
        var ny = y + dy[d]
        if (nx !in 0 until N || ny !in 0 until M) {
            d = (d+2)%4
            nx = x + dx[d]
            ny = y + dy[d]
        }
        x = nx
        y = ny

        when(d) {
            0 -> {
                newDice[0] = dice[3]
                newDice[1] = dice[1]
                newDice[2] = dice[0]
                newDice[3] = dice[5]
                newDice[4] = dice[4]
                newDice[5] = dice[2]
            }
            1 -> {
                newDice[0] = dice[1]
                newDice[1] = dice[5]
                newDice[2] = dice[2]
                newDice[3] = dice[3]
                newDice[4] = dice[0]
                newDice[5] = dice[4]
            }
            2 -> {
                newDice[0] = dice[2]
                newDice[1] = dice[1]
                newDice[2] = dice[5]
                newDice[3] = dice[0]
                newDice[4] = dice[4]
                newDice[5] = dice[3]
            }
            3 -> {
                newDice[0] = dice[4]
                newDice[1] = dice[0]
                newDice[2] = dice[2]
                newDice[3] = dice[3]
                newDice[4] = dice[5]
                newDice[5] = dice[1]
            }
        }
        dice = newDice

        getScore()

        if (dice[5] > board[x][y]) d = (d+1)%4
        else if (dice[5] < board[x][y]) d = (d+3)%4
    }

    fun getScore() {
        val n = board[x][y]
        val queue = mutableListOf(x to y)
        val visited = Array(N) { BooleanArray(M) {false} }
        visited[x][y] = true

        while (queue.isNotEmpty()) {
            val q = queue.removeFirst()
            answer += n
            repeat(4) {
                val nx = q.first + dx[it]
                val ny = q.second + dy[it]
                if (nx in 0 until N && ny in 0 until M && !visited[nx][ny] && board[nx][ny] == n) {
                    queue.add(nx to ny)
                    visited[nx][ny] = true
                }
            }
        }
    }
}