package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b20055 {
    var N = 0
    var K = 0
    lateinit var arr: IntArray
    var robots = mutableListOf<Int>()

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        K = input[1].toInt()
        arr = readLine().split(" ").map { it.toInt() }.toIntArray()
        var answer = 1
        while (rotate() && move() && newRobot() && checkArr()) answer++

        println(answer)
    }

    fun rotate(): Boolean {
        var ri = 0
        while (ri < robots.size) {
            robots[ri] = (robots[ri] + 1) % (2 * N)
            if (robots[ri] == N - 1) robots.removeAt(ri)
            else ri++
        }
        val temp = arr.last()
        for (i in arr.size- 1 downTo 1) {
            arr[i] = arr[i-1]
        }
        arr[0] = temp
        return true
    }

    fun move(): Boolean {
        var i = 0
        while (robots.isNotEmpty() && i < robots.size) {
            val next = (robots[i] + 1) % (2 * N)
            if (arr[next] > 0 && robots.findLast { it == next } == null) {
                if (next == N - 1) {
                    robots.removeAt(i)
                    arr[next]--
                }
                else {
                    robots[i] = next
                    arr[next]--
                    i++
                }
            }
            else i++
        }

        return true
    }

    fun newRobot(): Boolean {
        if (arr[0] > 0) {
            robots.add(0)
            arr[0]--
        }
        return true
    }
    fun checkArr() = arr.count { it == 0 } < K
    fun printBoard() {
        println("]-----] robots [-----[")
        println(robots)
        println("]-----] Arr [-----[")
        arr.map { print("$it ") }
        println()
    }
}