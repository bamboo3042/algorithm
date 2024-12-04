package programmers

import kotlin.math.pow

class `2차원 동전 뒤집기` {
    class Solution {
        fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
            var answer = Int.MAX_VALUE
            val boardCheck = fun(rowCheck: Int, columnCheck: Int): Boolean {
                for (i in beginning.indices) {
                    val rc = if (1.shl(i) and rowCheck == 0) 0 else 1
                    for (j in beginning[0].indices) {
                        val cc = if (1.shl(j) and columnCheck == 0) 0 else 1
                        if (((rc xor cc) xor beginning[i][j]) != target[i][j]) return false
                    }
                }
                return true
            }

            for (i in 0 until 2.0.pow(beginning.size).toInt()) {
                for (j in 0 until 2.0.pow(beginning[0].size).toInt()) {
                    val count = i.toString(2).count { it == '1' } + j.toString(2).count { it == '1' }
                    if (count < answer && boardCheck(i, j)) answer = count
                }
            }

            return if (answer == Int.MAX_VALUE) -1 else answer
        }
    }
}