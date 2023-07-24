package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b14002 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }
        val dp = Array(N) { 1 to -1 }
        var maxP = 0
        for (i in 1 until N) {
            var p = i
            for (j in p-1 downTo 0) {
                if (dp[j].first >= dp[p].first && arr[j] < arr[i]) p = j
            }
            if (p != i) {
                dp[i] = dp[p].first+1 to p
                if (dp[i].first > dp[maxP].first) maxP = i
            }
        }
        val result = mutableListOf<Int>()
        println(dp[maxP].first)
        do {
            result.add(arr[maxP])
            maxP = dp[maxP].second
        } while (maxP != -1)
        println(result.reversed().joinToString(" "))
    }
}