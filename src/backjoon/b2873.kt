package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b2873 {
    var N = 0
    var M = 0
    lateinit var board: Array<IntArray>
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(1, 0, -1, 0)
    val word = "RDLU"
    var isEnd = false
    var x = 0
    var y = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        N = input[0]
        M = input[1]
        board = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        board[0][0] = 0
        if (N%2 == 1) {
            var ts ="R"
            for (i in 0 until N-1) {
                print(ts.repeat(M-1))
                print("D")
                ts = if (ts == "R") "L" else "R"
            }
            print(ts.repeat(M-1))
        }
        else {
            if (M%2 == 1) {
                var ts = "D"
                for (j in 0 until M-1) {
                    print(ts.repeat(N-1))
                    print("R")
                    ts = if (ts == "D") "U" else "D"
                }
                print(ts.repeat(N-1))
            }
            else {
                var mx = 0
                var my = 1
                for (i in 0 until N) {
                    for (j in 0 until M) {
                        if ((i+j)%2 == 1 && board[i][j] < board[mx][my]) {
                            mx = i
                            my = j
                        }
                    }
                }
                board[mx][my] = 0
                var ts ="R"
                x = if (mx%2 == 0) mx else mx-1
                for (i in 0 until x) {
                    print(ts.repeat(M-1))
                    print("D")
                    ts = if (ts == "R") "L" else "R"
                }
                var isD = true
                var tx = x
                for (j in 0 until M) {
                    if (isD && (tx+1 != mx || j != my)) {
                        tx += 1
                        print("D")
                        isD = false
                    }
                    else if (!isD && (tx-1 != mx || j != my)) {
                        tx -= 1
                        print("U")
                        isD = true
                    }
                    if (j+1 < M) print("R")
                }
                if (mx !in N-2 until N) {
                    print("D")
                    ts = "L"
                    for (i in x + 2 until N - 1) {
                        print(ts.repeat(M - 1))
                        print("D")
                        ts = if (ts == "R") "L" else "R"
                    }
                    print(ts.repeat(M - 1))
                }
            }
        }
    }
}