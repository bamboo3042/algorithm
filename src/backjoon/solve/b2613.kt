package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2613 {
    var N = 0
    var M = 0
    lateinit var bubbles: IntArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        bubbles = readLine().split(" ").map { it.toInt() }.toIntArray()
        N = input[0].toInt()
        M = input[1].toInt()

        var min = 0
        var max = bubbles.sum()
        var answer = Int.MAX_VALUE
        var answerList = mutableListOf<Int>()
        var bm = bubbles.max()

        while (min < max) {
            val mid = (min + max) / 2
            if (mid < bm) {
                min = mid + 1
                continue
            }
            val tempList = mutableListOf<Int>()
            var temp = 0
            var cnt = 0

            for (i in bubbles) {
                if (temp + i <= mid) {
                    temp += i
                    cnt++
                }
                else {
                    tempList.add(cnt)
                    temp = i
                    cnt = 1
                }
            }
            if (cnt != 0) tempList.add(cnt)

            if (tempList.size > M) min = mid + 1
            else {
                if (mid < answer) {
                    answer = mid
                    answerList = tempList
                }
                max = mid
            }
        }

        while (answerList.size < M) {
            val i = answerList.indexOf(answerList.max())
            answerList[i] -= 1
            answerList.add(i, 1)
        }

        println(answer)
        println(answerList.joinToString(" "))
    }
}