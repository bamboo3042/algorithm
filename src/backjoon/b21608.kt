package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.security.SecureRandom

class b21608 {
    var N = 0
    lateinit var board: Array<IntArray>
    lateinit var students: Array<IntArray>
    val dx = arrayOf(-1, 0, 0, 1)
    val dy = arrayOf(0, -1, 1, 0)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
//    N = readLine().toInt()
//    board = Array(N) { IntArray(N) {0} }
//    students = Array(N * N) {
//        readLine().split(" ").map { it.toInt() }.toIntArray()
//    }

        makeTestCase(3)

        for (s in students) {
            val list = step1(s)
            if (list.size == 1) {
                val (x, y) = list.first()
                board[x][y] = s[0]
            }
            else {
                val (x, y) = step2(list)
                board[x][y] = s[0]
            }
            printBoard()
        }
        printBoard()

        println(getScore())
    }

    fun step1(s: IntArray): List<Pair<Int, Int>> {
        val likes = s.sliceArray(1 .. 4)
        val checkBoard = Array(N) { IntArray(N) {0} }
        var max = 0
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (board[i][j] == 0) {
                    repeat(4) {
                        val nx = i + dx[it]
                        val ny = j + dy[it]
                        if (nx in 0 until N && ny in 0 until N && board[nx][ny] in likes) max = max.coerceAtLeast(++checkBoard[i][j])
                    }
                }
            }
        }
        val result = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (checkBoard[i][j] == max && board[i][j] == 0) result.add(i to j)
            }
        }

        return result
    }

    fun step2(list: List<Pair<Int, Int>>): Pair<Int, Int> {
        val checkBoard = Array(N) { IntArray(N) {0} }
        var max = 0

        list.forEach { (i, j) ->
            repeat(4) {
                val nx = i + dx[it]
                val ny = j + dy[it]
                if (nx in 0 until N && ny in 0 until N && board[nx][ny] == 0) max = max.coerceAtLeast(++checkBoard[i][j])
            }
        }

        for (i in 0 until N) {
            for (j in 0 until N) {
                if (checkBoard[i][j] == max && board[i][j] == 0) return i to j
            }
        }
        return 0 to 0
    }

    fun getScore(): Int {
        val checkBoard = Array(N) { IntArray(N) {0} }
        for (i in 0 until N) {
            for (j in 0 until N) {
                val sl = students.first { it[0] == board[i][j] }.sliceArray(1 .. 4)
                repeat(4) {
                    val nx = i + dx[it]
                    val ny = j + dy[it]
                    if (nx in 0 until N && ny in 0 until N && board[nx][ny] in sl) checkBoard[i][j]++
                }
            }
        }
        return checkBoard.sumOf { l -> l.sumOf { if (it == 2) 10 else if (it == 3) 100 else if (it == 4) 1000 else it} }
    }

    fun printBoard() {
        println("]-----] Board [-----[")

        board.forEach { l ->
            l.forEach { print("${String.format("%2d", it)} ") }
            println()
        }
    }

    fun makeTestCase(n: Int) {
        N = n
        board = Array(N) { IntArray(N) {0} }
        val lists = MutableList<MutableList<Int>> (N * N) { mutableListOf() }
        val random = SecureRandom()
        var index = 0
        while (lists.last().size != 5) {
            val c = random.nextInt(n * n) + 1
            if (lists[index].size == 0) {
                val check = lists.find { it.isNotEmpty() && it.first() == c }
                if (check == null) lists[index].add(c)
                continue
            }
            else {
                if (!lists[index].contains(c)) lists[index].add(c)
                if (lists[index].size == 5) index++
            }
        }
        println("----- input -----")
        println(n)
        lists.map { l ->
            l.map { print("$it ") }
            println()
        }

        students = lists.map { it.toIntArray() }.toTypedArray()
    }
}