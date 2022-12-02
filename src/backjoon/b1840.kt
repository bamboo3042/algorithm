package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

class b1840 {
    var board = Array (9) { arrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1) }
    var boardPos: List<MutableList<MutableList<Int>>> = (0 .. 8).map { (0 .. 8).map { mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8) }.toMutableList() }
    var inputQueue = ArrayDeque<Array<Int>> ()

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        repeat(81) {
            val (a, b, c) = readLine().split(" ").map { it.toInt() }
            inputQueue.addLast(arrayOf(a-1, b-1, c-1))
        }

        println(solve())
    }

    fun solve(): Int {
        inputQueue.mapIndexed { index, (a, b, c) ->
            if(!(fillBoard(a, b, c) && checkRow() && checkColumn() && checkMiniBox())) {
                return index + 1
            }
        }
        return -1
    }

    fun fillBoard(a: Int, b: Int, c: Int): Boolean {
        if(board[a][b] != -1 || c > 8 || c < 0 ) return false
        board[a][b] = c
        boardPos[a][b] = mutableListOf()
        removeRow(a, c)
        removeColumn(b, c)
        removeMiniBox(a, b, c)
        return true
    }

    fun checkRow(): Boolean {
        for(i in 0 .. 8) {
            var checkBit = 0
            for(k in 0 .. 8) {
                for(j in 0 .. 8) {
                    if(board[i][j] == k || boardPos[i][j].contains(k)) {
                        checkBit = checkBit or (2.0).pow(k).toInt()
                        break
                    }
                }
            }

            if(checkBit != "111111111".toInt(2)) {
                return false
            }
        }

        return true
    }

    fun checkColumn(): Boolean {
        for(j in 0 .. 8) {
            var checkBit = 0
            for(k in 0 .. 8) {
                for(i in 0 .. 8) {
                    if(board[i][j] == k || boardPos[i][j].contains(k)) {
                        checkBit = checkBit or (2.0).pow(k).toInt()
                        break
                    }
                }
            }

            if(checkBit != "111111111".toInt(2)) {
                return false
            }
        }

        return true
    }

    fun checkMiniBox(): Boolean {
        for(a in listOf(0, 3, 6)) {
            for(b in listOf(0, 3, 6)) {
                var checkBit = 0
                for (i in a .. a + 2) {
                    for(k in 0 .. 8) {
                        for (j in b .. b + 2) {
                            if(board[i][j] == k || boardPos[i][j].contains(k)) {
                                checkBit = checkBit or (2.0).pow(k).toInt()
                                break
                            }
                        }
                    }
                }

                if(checkBit != "111111111".toInt(2)) {
                    return false
                }
            }
        }

        return true
    }

    fun removeRow(a: Int, n: Int) {
        for(i in 0 .. 8) {
            boardPos[a][i].remove(n)
        }
    }

    fun removeColumn(b: Int, n: Int) {
        for(i in 0 .. 8) {
            boardPos[i][b].remove(n)
        }
    }

    fun removeMiniBox(a: Int, b: Int, n: Int) {
        val x = (a / 3) * 3
        val y = (b / 3) * 3
        for (i in x .. x + 2) {
            for (j in y .. y + 2) {
                boardPos[i][j].remove(n)
            }
        }
    }
}