package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.absoluteValue

class b2467 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }.toIntArray()
        var left = 0
        var right = arr.size - 1
        var min = (arr[right] + arr[left]).absoluteValue
        var answer = arr[left] to arr[right]
        while (left < right) {
            if (min == 0) break

            val temp = arr[right] + arr[left]
            if (temp.absoluteValue < min) {
                min = temp.absoluteValue
                answer = arr[left] to arr[right]
            }

            if (temp > 0) right--
            else left++
        }

        println("${answer.first} ${answer.second}")
    }
}