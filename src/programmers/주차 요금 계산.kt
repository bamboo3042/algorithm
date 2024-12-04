package programmers

import kotlin.math.ceil

class `주차 요금 계산` {
    class Solution {
        fun solution(fees: IntArray, records: Array<String>): IntArray {
            val map = sortedMapOf<Int, MutableList<Int>>()
            val endTime = 23 * 60 + 59

            records.forEach { str ->
                val (time, number, _) = str.split(" ")
                val (h, m) = time.split(":")

                map.getOrPut(number.toInt()) { mutableListOf() }.add(h.toInt() * 60 + m.toInt())
            }


            return map.map { (_, list) ->
                var totalTime = -fees[0]
                var totalPrice = fees[1]

                for (i in 0 until list.size step 2) {
                    totalTime += if (i + 1 == list.size) endTime - list[i]
                    else list[i + 1] - list[i]
                }

                if (totalTime > 0) totalPrice += ceil(totalTime.toDouble() / fees[2]).toInt() * fees[3]

                totalPrice
            }.toIntArray()
        }
    }
}