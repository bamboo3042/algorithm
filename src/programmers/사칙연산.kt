package programmers

import kotlin.math.max
import kotlin.math.min

class 사칙연산 {
    class Solution {
        fun solution(arr: Array<String>): Int {
            val num = arr.filterIndexed { index, _ -> index % 2 == 0 }.map { it.toInt() }
            val opp = arr.filterIndexed { index, _ -> index % 2 == 1 }
            
            val dpMax = Array(num.size) { IntArray(num.size) {Int.MIN_VALUE} }
            val dpMin = Array(num.size) { IntArray(num.size) {Int.MAX_VALUE} }

            for (i in num.indices) {
                for (s in 0 until  num.size - i) {
                    val e = s + i
                    if (s == e) {
                        dpMax[s][e] = num[s]
                        dpMin[s][e] = num[s]
                    }

                    for (m in s until e) {
                        if (opp[m] == "+") {
                            dpMax[s][e] = max(dpMax[s][m] + dpMax[m+1][e], dpMax[s][e])
                            dpMin[s][e] = min(dpMin[s][m] + dpMin[m+1][e], dpMin[s][e])
                        }
                        else {
                            dpMax[s][e] = max(dpMax[s][m] - dpMin[m+1][e], dpMax[s][e])
                            dpMin[s][e] = min(dpMin[s][m] - dpMax[m+1][e], dpMin[s][e])
                        }
                    }
                }
            }

            return dpMax[0][num.size-1]
        }
    }
}