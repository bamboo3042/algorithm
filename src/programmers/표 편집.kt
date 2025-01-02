package programmers

import java.util.PriorityQueue
import kotlin.math.absoluteValue

class `표 편집` {
    class Solution {
        fun solution(n: Int, k: Int, cmd: Array<String>): String {
            val list = BooleanArray(n) { true }
            var temp = k
            val remove = ArrayDeque<Int>()
            var moveCount = 0
            val leftQueue = PriorityQueue<Int> { o1, o2 -> o2 - o1}
            val rightQueue = PriorityQueue<Int>()

            repeat(n) {
                if (it < k) leftQueue.offer(it)
                else if (it > k) rightQueue.offer(it)
            }

            fun moveFun() {
                if (moveCount > 0) {
                    repeat(moveCount) {
                        leftQueue.offer(temp)
                        temp = rightQueue.poll()
                    }
                }
                else {
                    repeat(moveCount.absoluteValue) {
                        rightQueue.offer(temp)
                        temp = leftQueue.poll()
                    }
                }

                moveCount = 0
            }

            cmd.forEach { s ->
                when(s[0]) {
                    'D' -> {
                        val (_, ns) = s.split(" ")

                        moveCount += ns.toInt()
                    }
                    'U' -> {
                        val (_, ns) = s.split(" ")

                        moveCount -= ns.toInt()
                    }
                    'C' -> {
                        moveFun()

                        list[temp] = false
                        remove.addLast(temp)

                        temp = if (rightQueue.isEmpty()) leftQueue.poll()
                        else rightQueue.poll()
                    }
                    'Z' -> {
                        moveFun()

                        val recent = remove.removeLast()

                        list[recent] = true

                        if (recent < leftQueue.peek()) leftQueue.offer(recent)
                        else rightQueue.offer(recent)
                    }
                    else -> throw Exception()
                }
            }

            return list.joinToString("") { if (it) "O" else "X" }
        }
    }
}