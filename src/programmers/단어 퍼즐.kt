package programmers

class `단어 퍼즐` {
    class Solution {
        fun solution(strs: Array<String>, t: String): Int {
            val dp = IntArray(t.length) { Int.MAX_VALUE}

            strs.forEach { str ->
                if(t.startsWith(str)) dp[str.length] = 1
            }

            for (i in t.indices) {
                val s = t.substring(i)

                strs.forEach { str ->
                    if (s.startsWith(str)) {
                        dp[i + str.length] = dp[i] + 1
                    }
                }
            }

            return if (dp.last() == Int.MAX_VALUE) -1 else dp.last()
        }
    }
}