package programmers

import java.util.PriorityQueue

class SkillCheck {
    class Solution {
        fun solution(info: IntArray, edges: Array<IntArray>): Int {
            var answer: Int = 0
            val tree = List(info.size) { mutableListOf<Int>() }
            edges.forEach { (p, v) ->
                tree[p].add(v)
            }
            val queue =  PriorityQueue<Pair<Int, Int>> { o1, o2 ->
                if (o1.second > o2.second) 1
                else if (o1.second == o2.second) 0
                else -1
            }
            var wolf = 0
            queue.add(0 to 0)
            while (queue.isNotEmpty()) {
                val (n, w) = queue.poll()
                println("n: $n, w: $w")

                if (info[n] == 0) answer++
                else wolf++

                if (wolf >= answer) return answer - 1

                val left = tree[n].getOrNull(0)
                val right = tree[n].getOrNull(1)

                if (left != null) {
                    if (info[left] == 0) queue.add(left to 0)
                    else queue.add(left to w+1)
                }
                if (right != null) {
                    if (info[right] == 0) queue.add(right to 0)
                    else queue.add(right to w+1)
                }
            }
            return answer
        }
    }
}