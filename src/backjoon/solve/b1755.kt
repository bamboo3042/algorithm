package backjoon.solve

import java.util.*

class b1755 {
    fun main() = with(Scanner(System.`in`)) {
        val (M, N) = readln().split(" ").map { it.toInt() }
        val words = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val list = (M .. N).sortedBy { n ->
            var temp = ""
            for (s in n.toString()) temp += words[s.code - 48]
            temp
        }
        val result = StringBuilder()
        for ((count, n) in list.withIndex()) {
            result.append("$n ")
            if ((count + 1) % 10 == 0) result.appendLine()
        }
        println(result)
    }
}