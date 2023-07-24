package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2011 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val str = readLine()
        if (str.isEmpty() || str[0] == '0') println(0)
        else if (str.length == 1) println(1)
        else {
            val dp = IntArray(str.length) { 0 }
            dp[0] = 1
            val start = (str[0] - '0') * 10 + (str[1] - '0')
            if (str[1] == '0') {
                if (str[0] != '1' && str[0] != '2') dp[1] = 0
                else dp[1] = 1
            }
            else if (start in 10 .. 26) dp[1] = 2
            else dp[1] = 1

            for (i in 2 until str.length) {
                if (str[i] == '0' && str[i-1] != '1' && str[i-1] != '2') {
                    dp[str.length-1] = 0
                    break
                }
                if (str[i] != '0') dp[i] = (dp[i] + dp[i-1]) % 1000000

                val temp = (str[i-1] - '0') * 10 + (str[i] - '0')
                if (temp in 10 .. 26) dp[i] = (dp[i] + dp[i-2]) % 1000000
            }

            println(dp.last())
        }
    }
}