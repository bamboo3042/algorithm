package programmers

class 올바른_괄호 {

    class Solution {
        fun solution(s: String): Boolean {
            var l = 0
            var r = 0
            s.forEach { c ->
                if (c == '(') l++
                else r++

                if (l < r) return false
            }
            return l == r
        }
    }

}