package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b15684 {
    var N = 0
    var M = 0
    var H = 0
    var answer = Int.MAX_VALUE
    lateinit var ladder: Array<BooleanArray>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        H = input[2].toInt()
        ladder = Array(H) { BooleanArray(N-1) { false } }
        repeat(M) {
            val st = StringTokenizer(readLine())
            ladder[st.nextToken().toInt() - 1][st.nextToken().toInt() - 1] = true
        }
        dfs(0, 0, -1)

        if(answer == Int.MAX_VALUE) println(-1)
        else println(answer)
    }

    fun dfs(time: Int, h: Int, n: Int) {
        if (lineCheck()) answer = answer.coerceAtMost(time)
        else if (time != 3) {
            for (i in h until H) {
                var j = if(i == h) n else -1
                while(++j < N - 1) {
                    if( !ladder[i][j] && !(j - 1 >= 0  && ladder[i][j-1]) && !(j + 2 < N && ladder[i][j+1])) {
                        ladder[i][j] = true
                        dfs(time + 1, i, j)
                        ladder[i][j] = false
                    }
                }
            }
        }
    }

    fun lineCheck(): Boolean {
        for (l in 0 until N) {
            var x = l
            for(i in 0 until H) {
                if (x + 1 < N && ladder[i][x]) x += 1
                else if (x - 1 >= 0 && ladder[i][x-1]) x -=1
            }
            if (x != l) return false
        }
        return true
    }
}