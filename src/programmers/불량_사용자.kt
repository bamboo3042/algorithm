package programmers

class 불량_사용자 {
    class Solution {

        lateinit var banList: Array<IntArray>
        lateinit var visited: BooleanArray
        var N = 0
        var answer = 0

        fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
            visited = BooleanArray(1.shl(user_id.size)) {false}
            N = banned_id.size
            banList = Array(banned_id.size) { bi ->
                val n = banned_id[bi].length

                val temp = user_id.mapIndexedNotNull { ui, user ->
                    if (user.length != n) return@mapIndexedNotNull null
                    for (i in 0 until n) {
                        if (banned_id[bi][i] == '*') continue

                        if (banned_id[bi][i] != user[i]) return@mapIndexedNotNull null
                    }

                    ui
                }

                temp.toIntArray()
            }

            dfs(0, 0)

            return answer
        }

        fun dfs(n: Int, list: Int) {
            visited[list] = true

            if (n == N) {
                answer += 1
                return
            }


            for (i in banList[n]) {
                if (1.shl(i) and list == 0 && !visited[1.shl(i) or list]) {
                    dfs(n+1, list or 1.shl(i))
                }
            }
        }
    }
}