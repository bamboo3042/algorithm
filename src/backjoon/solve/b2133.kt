package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2133 {
    lateinit var dp: IntArray

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        dp = IntArray(n+1) {0}
        if (n % 2 == 1) println(0)
        else if (n == 2) println(3)
        else {
            dp[0] = 1
            dp[2] = 3
            for (i in 4 .. n step 2) {
                dp[i] = dp[i-2] * dp[2]
                for (j in i-4 downTo 0 step 2) {
                    dp[i] += dp[j] * 2
                }
            }
            println(dp[n])
        }
    }
}