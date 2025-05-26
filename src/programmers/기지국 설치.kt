package programmers

class `기지국 설치` {
    class Solution {
        fun solution(n: Int, stations: IntArray, w: Int): Int {
            var answer = 0
            var position = 1
            var index = 0

            while (position <= n) {
                if (index < stations.size && position >= stations[index] - w) {
                    position = stations[index] + w + 1
                    index++
                } else {
                    answer++
                    position += 2 * w + 1
                }
            }

            return answer
        }
    }

}