package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.absoluteValue

class b14890 {
    var n = 0
    var l = 0
    lateinit var board: Array<IntArray>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = StringTokenizer(readLine())
        n = input.nextToken().toInt()
        l = input.nextToken().toInt()

        board = Array(n) {
            val st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        var sum = 0

        for (i in 0 until n) {
            if(checkRow(i)) sum++
            if(checkColumn(i)) sum++
        }

        println(sum)
    }

    fun checkRow(i: Int): Boolean {
        val checks = Array(n) { false }
        var j = -1
        while (++j < n - 1) {
            val step = (board[i][j] - board[i][j+1])
            if(step == 0) continue
            if(step.absoluteValue != 1) return false
            if(step == 1) {
                if(j + l >= n) return false
                for (k in j + 1 .. j + l) {
                    if(board[i][k] != board[i][j+1]) return false
                    if(checks[k]) return false

                    checks[k] = true
                }
                j = j + l - 1
            }
            if (step == -1) {
                if(j - l + 1 < 0) return false
                for (k in j downTo j - l + 1) {
                    if(board[i][k] != board[i][j]) return false
                    if(checks[k]) return false

                    checks[k] = true
                }
            }
        }
        return true
    }

    fun checkColumn(i: Int): Boolean {
        val checks = Array(n) { false }
        var j = -1
        while (++j < n - 1) {
            val step = (board[j][i] - board[j+1][i])
            if(step == 0) continue
            if(step.absoluteValue != 1) return false
            if(step == 1) {
                if(j + l >= n) return false
                for (k in j + 1 .. j + l) {
                    if(board[k][i] != board[j+1][i]) return false
                    if(checks[k]) return false

                    checks[k] = true
                }
                j = j + l - 1
            }
            if (step == -1) {
                if(j - l + 1 < 0) return false
                for (k in j downTo j - l + 1) {
                    if(board[k][i] != board[j][i]) return false
                    if(checks[k]) return false

                    checks[k] = true
                }
            }
        }
        return true
    }
}