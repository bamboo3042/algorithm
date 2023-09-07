package programmers

class 폰켓몬 {
    class Solution {
        fun solution(nums: IntArray): Int {
            return nums.toHashSet().size.coerceAtLeast(nums.size / 2)
        }
    }
}