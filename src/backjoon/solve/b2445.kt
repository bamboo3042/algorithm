package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2445 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val sb = StringBuilder()
        repeat(n - 1) {
            repeat(it + 1) { sb.append("*") }
            repeat(2 * (n - it - 1)) { sb.append(" ") }
            repeat(it + 1) { sb.append("*") }
            sb.appendLine()
        }
        repeat(n) {
            repeat(n - it) { sb.append("*") }
            repeat(2 * it) { sb.append(" ") }
            repeat(n - it) { sb.append("*") }
            sb.appendLine()
        }
        println(sb)
    }
}