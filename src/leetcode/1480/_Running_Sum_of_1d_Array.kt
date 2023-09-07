package leetcode.`1480`

class _Running_Sum_of_1d_Array {
    class Solution {
        fun runningSum(nums: IntArray): IntArray {
            return nums.fold(intArrayOf()) { acc: IntArray, i: Int -> if (acc.isEmpty()) intArrayOf(i) else acc + (i + acc.last()) }
        }
    }
}