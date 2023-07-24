package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    println(
        Solution().solution(3, 5,	arrayOf(intArrayOf(10, 60, 1), intArrayOf(15, 100, 3), intArrayOf(20, 30, 1), intArrayOf(30, 50, 3), intArrayOf(50, 40, 1), intArrayOf(60, 30, 2), intArrayOf(65, 30, 1), intArrayOf(70, 100, 2)))
    )
}

