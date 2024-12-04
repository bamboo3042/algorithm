package programmers

class `택배 배달과 수거하기` {
    class Solution {
        fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
            var answer: Long = 0

            var dr = deliveries.indexOfLast { it != 0 }
            var pr = pickups.indexOfLast { it != 0 }

            while (dr != -1 && pr != -1) {
                answer += (maxOf(dr, pr) + 1) * 2

                dr = rMove(deliveries, cap, dr)
                pr = rMove(pickups, cap, pr)
            }

            while (dr != -1) {
                answer += (dr + 1) * 2
                dr = rMove(deliveries, cap, dr)
            }

            while (pr != -1) {
                answer += (pr + 1) * 2
                pr = rMove(pickups, cap, pr)
            }

            return answer
        }

        private fun rMove(array: IntArray, cap: Int, right: Int): Int {
            var c = cap

            for (i in right downTo 0) {
                if (array[i] > c) {
                    array[i] -= c
                    return i
                }
                else if (array[i] == c) {
                    array[i] = 0

                    return array.findZeroFromLast(i)
                }
                else {
                    c -= array[i]
                    array[i] = 0
                }
            }

            return -1
        }

        private fun IntArray.findZeroFromLast(index: Int): Int {
            for (i in index downTo 0) {
                if (this[i] == 0) return i
            }

            return -1
        }
    }
}