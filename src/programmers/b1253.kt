package programmers

import java.io.BufferedReader
import java.io.InputStreamReader

class b1253 {
    lateinit var arr: List<Int>
    var N = 0

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        arr = readLine().split(" ").map { it.toInt() }.sorted()

        var answer = 0

        for (i in 0 until N) {
            var left = 0
            var right = arr.size - 1
            while (left < right) {
                val sum = arr[left] + arr[right]

                if (sum == arr[i]) {
                    if (left == i) left++
                    else if (right == i) right--
                    else {
                        answer++
                        break
                    }
                }
                else if (sum < arr[i]) left++
                else right--
            }
        }

        println(answer)
    }
}