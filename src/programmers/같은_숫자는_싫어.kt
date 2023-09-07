package programmers

class 같은_숫자는_싫어 {

    class Solution {
        fun solution(arr: IntArray): IntArray {
            return arr
                .fold(listOf()) { acc: List<Int>, i: Int ->
                    if (acc.isEmpty()) listOf(i)
                    else if (acc.last() != i) acc + i
                    else acc
                }.toIntArray()
        }
    }

}