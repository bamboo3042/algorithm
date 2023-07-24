package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2442 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val sb = StringBuilder()
        for (i in n - 1 downTo 0) {
            repeat(i) { sb.append(" ") }
            repeat(n - i - 1) { sb.append("*") }
            sb.append("*")
            repeat(n - i - 1) { sb.append("*") }
            sb.appendLine()
        }
        println(sb)
    }
}