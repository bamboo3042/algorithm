package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2143 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val T = readLine().toInt()
        val n = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }
        val m = readLine().toInt()
        val brr = readLine().split(" ").map { it.toInt() }
        val sumA = mutableListOf<Int>()
        val sumB = mutableListOf<Int>()
        for (i in 0 until n) {
            var temp = 0
            for (j in i until n) {
                temp += arr[j]
                sumA.add(temp)
            }
        }

        for (i in 0 until m) {
            var temp = 0
            for (j in i until m) {
                temp += brr[j]
                sumB.add(temp)
            }
        }

        sumB.sort()

        var result = 0L
        for (a in sumA) {
            val t = T - a
            val left = binarySearchStartIndex(sumB, t)
            val right = binarySearchEndIndex(sumB, t)

            if (left == -1 || right == -1 || sumB[left] != t) continue

            result += right - left + 1
        }

        println(result)
    }

    fun binarySearchStartIndex(arr: List<Int>, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        var result = -1

        while (left <= right) {
            val mid = (left + right) / 2
            if (arr[mid] >= target) {
                right = mid - 1
                result = mid
            } else {
                left = mid + 1
            }
        }

        return result
    }

    fun binarySearchEndIndex(arr: List<Int>, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        var result = -1

        while (left <= right) {
            val mid = (left + right) / 2
            if (arr[mid] <= target) {
                left = mid + 1
                result = mid
            } else {
                right = mid - 1
            }
        }

        return result
    }
}