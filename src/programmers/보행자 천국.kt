package programmers

class `보행자 천국` {
    class Solution {
        val MOD: Int = 20170805
        fun solution(m: Int, n: Int, cityMap: Array<IntArray>): Int {
            val board = Array(m) { IntArray(n) { 0 } }
            val list = mutableListOf<Move>()
            val visited = Array(m) { IntArray(n) { 0 } }
            val lBit = 1
            val uBit = 2

            board[0][0] = 1
            list.add(Move(0, 0, true))
            list.add(Move(0, 0, false))

            while (list.isNotEmpty()) {
                val (tm, tn, isLeft) = list.removeFirst()

                println("$tm $tn $isLeft ${board[tm][tn]}")

                if (tm < m - 1) {
                    if (cityMap[tm+1][tn] != 1) {
                        if (cityMap[tm+1][tn] == 2) {
                            if (tm < m - 2 && !isLeft) {
                                if (visited[tm+2][tn] and uBit == 0) {
                                    board[tm+2][tn] = (board[tm][tn] + board[tm+2][tn]) % MOD
                                    visited[tm+2][tn] += uBit
                                    list.add(Move(tm+2, tn, false))
                                }
                            }
                        }
                        else if (visited[tm+1][tn] and uBit == 0) {
                            board[tm+1][tn] = (board[tm][tn] + board[tm+1][tn]) % MOD
                            visited[tm+1][tn] += uBit
                            list.add(Move(tm+1, tn, false))
                        }
                    }
                }

                if (tn < n - 1) {
                    if (cityMap[tm][tn+1] != 1) {
                        if (cityMap[tm][tn+1] == 2) {
                            if (tn < n - 2 && isLeft) {
                                if (visited[tm][tn+2] and lBit == 0) {
                                    board[tm][tn+2] = (board[tm][tn] + board[tm][tn+2]) % MOD
                                    visited[tm][tn+2] += lBit
                                    list.add(Move(tm, tn+2, true))
                                }
                            }
                        }
                        else if (visited[tm][tn+1] and lBit == 0) {
                            board[tm][tn+1] = (board[tm][tn] + board[tm][tn+1]) % MOD
                            visited[tm][tn+1] += lBit
                            list.add(Move(tm, tn+1, true))
                        }
                    }
                }
            }

            return board[m-1][n-1]
        }

        data class Move(
            val m: Int,
            val n: Int,
            val isLeft: Boolean
        )
    }
}