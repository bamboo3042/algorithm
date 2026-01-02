package programmers

class `공 이동 시뮬레이션` {
    class Solution {
        fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
            var rs = x.toLong()
            var re = x.toLong()
            var cs = y.toLong()
            var ce = y.toLong()
            val maxR = n - 1L
            val maxC = m - 1L

            for (i in queries.indices.reversed()) {
                val command = queries[i][0]
                val dx = queries[i][1].toLong()

                when (command) {
                    0 -> {
                        if (cs != 0L) cs += dx
                        ce += dx
                    }
                    1 -> {
                        cs -= dx
                        if (ce != maxC) ce -= dx
                    }
                    2 -> {
                        if (rs != 0L) rs += dx
                        re += dx
                    }
                    3 -> {
                        rs -= dx
                        if (re != maxR) re -= dx
                    }
                }

                rs = rs.coerceAtLeast(0)
                re = re.coerceAtMost(maxR)
                cs = cs.coerceAtLeast(0)
                ce = ce.coerceAtMost(maxC)

                if (rs > re || cs > ce) return 0L
            }

            return (re - rs + 1) * (ce - cs + 1)
        }
    }
}