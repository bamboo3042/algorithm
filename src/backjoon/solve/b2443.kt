package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2443 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val sb = StringBuilder()
        repeat(n) {
            repeat(it) { sb.append(" ") }
            repeat(2 * (n - it) - 1) { sb.append("*") }
            sb.appendLine()
        }
        println(sb)
    }
}