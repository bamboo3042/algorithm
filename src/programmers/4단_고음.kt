package programmers

import kotlin.math.pow

class `4단_고음` {

    class Solution {
        fun solution(n: Int): Int {
            return dfs(n-2, 2)
        }

        fun dfs(n: Int, p: Int): Int {
            if (n <= 3) {
                return if (p == 2) 1
                else 0
            }

            if (3.0.pow(p/2) > n) return 0

            var sum = 0

            if (n % 3 == 0 && p >= 2) sum += dfs(n/3, p-2)
            sum += dfs(n-1, p+1)

            return sum
        }
    }
}