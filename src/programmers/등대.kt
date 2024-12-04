package programmers

import java.util.PriorityQueue

class 등대 {
    class Solution {
        fun solution(n: Int, lighthouse: Array<IntArray>): Int {
            var answer: Int = 0
            val board = Array(n+1) { mutableSetOf<Int>() }
            val countRoad = mutableMapOf<Int, Int>()
            val list = mutableSetOf<Int>()

            lighthouse.forEach { (a, b) ->
                board[a].add(b)
                board[b].add(a)
                countRoad[a] = (countRoad[a] ?: 0) + 1
                countRoad[b] = (countRoad[b] ?: 0) + 1
            }

            countRoad.map { (k, v) -> if (v == 1) list.add(k) }

            while (countRoad.isNotEmpty()) {

                list.forEach { temp ->
                    if (countRoad[temp] == 0) return@forEach
                    if (board[temp].isEmpty()) return@forEach

                    answer++

                    val target = board[temp].first()

                    board[target].forEach { n ->
                        board[n].remove(target)
                        countRoad[n] = countRoad[n]!! - 1
                    }

                    board[target].clear()
                    countRoad.remove(target)
                }

                list.clear()
                countRoad.keys.toList().forEach { k ->
                    when(countRoad[k]) {
                        1 -> list.add(k)
                        0 -> countRoad.remove(k)
                    }
                }
            }

            return answer
        }
    }
}