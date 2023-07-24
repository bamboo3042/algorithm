package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1062 {
    var N = 0
    var K = 0
    lateinit var words: Array<String>
    lateinit var checks: BooleanArray
    var answer = 0


    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        K = input[1].toInt()
        words = Array(N) { readLine() }
        checks = BooleanArray(26) { false }
        dfs(0, 0)
        println(answer)
    }

    fun dfs(index: Int, n: Int) {
        if (K == n) answer = answer.coerceAtLeast(words.count { w -> wordCheck(w) })
        else {
            for (i in index until checks.size) {
                checks[i] = true
                dfs(i+1, n+1)
                checks[i] = false
            }
        }
    }

    fun wordCheck(w: String): Boolean {
        w.forEach { c -> if (!checks[c-'a']) return false }
        return true
    }
}