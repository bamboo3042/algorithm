package programmers

class `행렬과 연산` {
    class Solution {
        fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {
            val answer = Array(rc.size) { IntArray(rc[0].size) { 0 } }
            val board = ArrayDeque<ArrayDeque<Int>>()
            val leftBoard = ArrayDeque<Int>()
            val rightBoard = ArrayDeque<Int>()

            rc.forEach { list ->
                val temp = ArrayDeque<Int> ()
                list.forEachIndexed { index, i ->
                    if (index == 0) leftBoard.addLast(i)
                    else if (index == list.size - 1) rightBoard.addLast(i)
                    else temp.addLast(i)
                }
                board.addLast(temp)
            }

            val shift = fun () {
                leftBoard.addFirst(leftBoard.removeLast())
                board.addFirst(board.removeLast())
                rightBoard.addFirst(rightBoard.removeLast())
            }
            val rotate = fun () {
                board.first().addFirst(leftBoard.removeFirst())
                rightBoard.addFirst(board.first().removeLast())
                board.last().addLast(rightBoard.removeLast())
                leftBoard.addLast(board.last().removeFirst())
            }

            operations.forEach { s -> if (s == "ShiftRow") shift() else rotate() }

            for (i in rc.indices) {
                var j = 0

                answer[i][j++] = leftBoard.removeFirst()

                board.removeFirst().forEach { answer[i][j++] = it }

                answer[i][j] = rightBoard.removeFirst()
            }

            return answer
        }
    }
}