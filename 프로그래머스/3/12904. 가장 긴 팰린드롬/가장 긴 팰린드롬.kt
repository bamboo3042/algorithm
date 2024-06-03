class Solution {
    fun solution(s: String): Int {
        var answer = 1

        for (i in s.indices) {
            for (j in 1 until s.length) {
                val left = i-j
                val right = i+j
                if (left < 0 || right >= s.length) break
                if (s[left] != s[right]) break
                answer = answer.coerceAtLeast(right - left + 1)
            }

            if (i < s.length - 1 && s[i] == s[i+1]) {
                answer = answer.coerceAtLeast(2)
                for (j in 1 until s.length) {
                    val left = i-j
                    val right = i+j+1

                    if (left < 0 || right >= s.length) break
                    if (s[left] != s[right]) break
                    answer = answer.coerceAtLeast(right - left + 1)
                }
            }
        }

        return answer
    }
}