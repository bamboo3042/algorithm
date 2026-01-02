package programmers

class `서버 증설 횟수` {
    class Solution {
        fun solution(players: IntArray, m: Int, k: Int): Int {
            var answer: Int = 0

            var serverCount = 0
            var server = sortedMapOf<Int, Int>()

            players.forEachIndexed { i, player ->

                val needServerCount = (player / m)

                println("$i, $player, $needServerCount, $serverCount, $answer")

                if (needServerCount > serverCount) {
                    val addServerCount = needServerCount - serverCount
                    server[i + k] = addServerCount
                    serverCount = needServerCount
                    answer += addServerCount
                }

                serverCount -= server.getOrDefault(i, 0)
            }

            return answer
        }
    }
}