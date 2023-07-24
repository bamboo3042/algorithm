package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b9011 {
    var N = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        repeat(readLine().toInt()) {
            N = readLine().toInt()
            val numbers = MutableList(N) {it+1}
            val list = readLine().split(" ").map { it.toInt() }
            val answer = MutableList(N) {0}
            for (i in numbers.indices.reversed()) {
                if (list[i] > numbers.size - 1) break
                else {
                    answer[i] = numbers.removeAt(list[i])
                }
            }
            if (answer.contains(0)) println("IMPOSSIBLE")
            else println(answer.joinToString(" "))
        }
    }
}