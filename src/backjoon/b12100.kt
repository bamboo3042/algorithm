package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b12100 {
    var n: Int = 0
    var answer = -1

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine()
        n = input.toInt()
        val board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }

        dfs(0, board)

        println(answer)
    }

    fun dfs(num: Int, board: Array<IntArray>) {
        if(num == 5) board.map { it.map { num -> answer = answer.coerceAtLeast(num) } }
        else{
            dfs(num + 1, left(board))
            dfs(num + 1, right(board))
            dfs(num + 1, up(board))
            dfs(num + 1, down(board))
        }
    }

    fun left(prevBoard: Array<IntArray>): Array<IntArray> {
        val board = Array(n) { prevBoard[it].clone() }
        for(i in 0 until n) {
            var b = 0
            var v = board[i][b]
            for (j in 1 until n) {
                if(board[i][j] == 0) continue
                if(board[i][j] == v) {
                    board[i][b] = v * 2
                    board[i][j] = 0
                    v = board[i][++b]
                }
                else if(board[i][j] != v) {
                    if(v != 0) ++b
                    v = board[i][j]
                    board[i][j] = 0
                    board[i][b] = v
                }
            }
        }
        return board
    }

    fun right(prevBoard: Array<IntArray>): Array<IntArray> {
        val board = Array(n) { prevBoard[it].clone() }
        for(i in 0 until n) {
            var b = n - 1
            var v = board[i][b]
            for (j in n - 2 downTo 0) {
                if(board[i][j] == 0) continue
                if(board[i][j] == v) {
                    board[i][b] = v * 2
                    board[i][j] = 0
                    v = board[i][--b]
                }
                else if(board[i][j] != v) {
                    if(v != 0) --b
                    v = board[i][j]
                    board[i][j] = 0
                    board[i][b] = v
                }
            }
        }
        return board
    }

    fun up(prevBoard: Array<IntArray>): Array<IntArray> {
        val board = Array(n) { prevBoard[it].clone() }
        for(j in 0 until n) {
            var a = 0
            var v = board[a][j]
            for (i in 1 until n) {
                if(board[i][j] == 0) continue
                if(board[i][j] == v) {
                    board[a][j] = v * 2
                    board[i][j] = 0
                    v = board[++a][j]
                }
                else if(board[i][j] != v) {
                    if(v != 0) a++
                    v = board[i][j]
                    board[i][j] = 0
                    board[a][j] = v
                }
            }
        }

        return board
    }

    fun down(prevBoard: Array<IntArray>): Array<IntArray> {
        val board = Array(n) { prevBoard[it].clone() }
        for(j in 0 until n) {
            var a = n - 1
            var v = board[a][j]
            for (i in n - 2 downTo 0) {
                if(board[i][j] == 0) continue
                if(board[i][j] == v) {
                    board[a][j] = v * 2
                    board[i][j] = 0
                    v = board[--a][j]
                }
                else if(board[i][j] != v) {
                    if(v != 0) --a
                    v = board[i][j]
                    board[i][j] = 0
                    board[a][j] = v
                }
            }
        }
        return board
    }
}