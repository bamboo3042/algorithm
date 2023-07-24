package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b17298 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }
        val result = IntArray(N) {-1}
        val stack = Stack<Int> ()
        for (i in N-1 downTo 0) {
            while (stack.isNotEmpty() && stack.peek() <= arr[i]) stack.pop()

            if (stack.isEmpty()) stack.push(arr[i])
            else result[i] = stack.peek()

            stack.push(arr[i])
        }

        println(result.joinToString(" "))
    }
}