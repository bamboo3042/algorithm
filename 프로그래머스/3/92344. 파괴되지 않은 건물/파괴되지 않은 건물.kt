class Solution {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        var answer = 0
        val map = Array(board.size+1) { IntArray(board[0].size+1) {0} }
        skill.forEach { l ->
            val damage = if (l[0] == 1) -l[5] else l[5]

            map[l[1]][l[2]] += damage
            map[l[3]+1][l[2]] += -damage
            map[l[1]][l[4]+1] += -damage
            map[l[3]+1][l[4]+1] += damage
        }

        for (i in board.indices) {
            for (j in 1 until board[i].size) {
                map[i][j] += map[i][j-1]
            }
        }

        for (j in board[0].indices) {
            for (i in 1 until board.size) {
                map[i][j] += map[i-1][j]
            }
        }

        for (i in board.indices) {
            for (j in board[i].indices) {
                board[i][j] += map[i][j]
                if (board[i][j] > 0) answer++
            }
        }

        return answer
    }
}