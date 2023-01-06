package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b6549 {
    var N = 0L

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        while(true) {
            val input = readLine().split(" ").map { it.toLong() }
            if(input[0] == 0L) break
            N = input[0]
            val stack = ArrayDeque<Long> ()
            stack.addLast(-1)
            val heights = input.subList(1, input.size) + -1
            var answer = 0L
            for (i in heights.indices) {
                while (stack.size > 1 && heights[i] < heights[stack.last().toInt()]) {
                    val h = stack.removeLast()
                    val l = stack.last()
                    answer = answer.coerceAtLeast((i - l - 1) * heights[h.toInt()])
                }
                stack.addLast(i.toLong())
            }
            println(answer)
        }
    }
}