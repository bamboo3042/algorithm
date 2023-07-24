package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1509 {
    lateinit var dp: Array<BooleanArray>
    lateinit var str: String

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        str = readLine()
        dp = Array(str.length) { BooleanArray(str.length) { false } }
        for (i in str.indices) dp[i][i] = true
        for (i in 0 until str.length-1) dp[i][i + 1] = str[i] == str[i + 1]
        for (i in 2 until str.length) {
            for (j in 0 until str.length - i) {
                dp[j][j + i] = (str[j] == str[j + i] && dp[j + 1][j + i - 1])
            }
        }

        println(dac())
    }

    fun dac(): Int {
        val arr = IntArray(str.length) { it+1 }

        for (e in 1 until str.length) {
            if (dp[0][e]) {
                arr[e] = 1
                continue
            }
            arr[e] = arr[e-1] + 1
            for (s in 1 .. e) {
                if (dp[s][e]) arr[e] = minOf(arr[e], arr[s-1] + 1)
            }
        }

        return arr.last()
    }
}