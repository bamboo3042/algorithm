package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b14888 {
    var N = 0
    lateinit var numbers: IntArray
    lateinit var operators: IntArray
    var max = Int.MIN_VALUE
    var min = Int.MAX_VALUE

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        numbers = readLine().split(" ").map { it.toInt() }.toIntArray()
        operators = readLine().split(" ").map { it.toInt() }.toIntArray()
        solve(1, numbers[0])
        println(max)
        println(min)
    }

    fun solve(index: Int, sum: Int) {
        if (index == numbers.size) {
            max = max.coerceAtLeast(sum)
            min = min.coerceAtMost(sum)
        }
        else {
            for (i in 0 until 4) {
                if (operators[i] > 0) {
                    operators[i]--
                    solve(index + 1, when(i) {
                        0 -> sum + numbers[index]
                        1 -> sum - numbers[index]
                        2 -> sum * numbers[index]
                        else -> sum / numbers[index]
                    })
                    operators[i]++
                }
            }
        }
    }
}