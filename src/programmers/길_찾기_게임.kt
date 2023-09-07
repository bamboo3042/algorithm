package programmers

class 길_찾기_게임 {
    class Solution {
        lateinit var list: List<MutableList<IntArray>>

        fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
            var answer = arrayOf<IntArray>()
            list = List(100001) { mutableListOf() }
            var max = 0
            nodeinfo.forEachIndexed { index, (x, y) ->
                list[y].add(intArrayOf(index+1, x))
                max = maxOf(max, y)
            }

            return arrayOf(
                preOrder(list[max][0][1], max, list[max][0][0], -1, 100001),
                postOrder(list[max][0][1], max, list[max][0][0], -1, 100001)
            )
        }

        fun preOrder(x: Int, y: Int, n: Int, l: Int, r: Int): IntArray {
            println("x: $x y: $y n: $n l: $l r: $r")
            for (i in y-1 downTo 0) {
                if (list[i].isNotEmpty()) {
                    val left = list[i].find { it[1] in l until x}
                    val right = list[i].find { it[1] in x until r }
                    if (left != null || right != null) {
                        val l1 = if (left == null) intArrayOf() else preOrder(left[1], i, left[0], l, x)
                        val l2 = if (right == null) intArrayOf() else preOrder(right[1], i, right[0], x, r)
                        return intArrayOf(n) + l1 + l2
                    }
                }
            }
            return intArrayOf(n)
        }

        fun postOrder(x: Int, y: Int, n: Int, l: Int, r: Int): IntArray {
            for (i in y-1 downTo 0) {
                if (list[i].isNotEmpty()) {
                    val left = list[i].find { it[1] in l until x}
                    val right = list[i].find { it[1] in x until r }
                    if (left != null || right != null) {
                        val l1 = if (left == null) intArrayOf() else postOrder(left[1], i, left[0], l, x)
                        val l2 = if (right == null) intArrayOf() else postOrder(right[1], i, right[0], x, r)
                        return l1 + l2 + intArrayOf(n)
                    }
                }
            }
            return intArrayOf(n)
        }
    }
}