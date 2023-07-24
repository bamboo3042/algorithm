package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b5373 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        repeat(readLine().toInt()) {
            val n = readLine().toInt()
            val moves = readLine().split(" ")
            var r = Array(3) { Array(3) { "r" } }
            var w = Array(3) { Array(3) { "w" } }
            var b = Array(3) { Array(3) { "b" } }
            var y = Array(3) { Array(3) { "y" } }
            var g = Array(3) { Array(3) { "g" } }
            var o = Array(3) { Array(3) { "o" } }
            var d: Int

            moves.mapIndexed { index, move ->
                when(move) {
                    "U+", "U-" -> {
                        if (move == "U+") {
                            w = rotateR(w)
                            d = 0
                        }
                        else {
                            w = rotateL(w)
                            d = 1
                        }
                        changePlain(arrayOf(r[0][2], r[0][1], r[0][0]), arrayOf(g[2][2], g[1][2], g[0][2]), o[2], arrayOf(b[0][0], b[1][0], b[2][0]), d).run {
                            r[0][2] = this[0][0]
                            r[0][1] = this[0][1]
                            r[0][0] = this[0][2]
                            g[2][2] = this[1][0]
                            g[1][2] = this[1][1]
                            g[0][2] = this[1][2]
                            o[2] = this[2]
                            b[0][0] = this[3][0]
                            b[1][0] = this[3][1]
                            b[2][0] = this[3][2]
                        }
                    }
                    "D+", "D-" -> {
                        if (move == "D+") {
                            y = rotateR(y)
                            d = 0
                        }
                        else {
                            y = rotateL(y)
                            d = 1
                        }
                        changePlain(r[2], arrayOf(b[2][2], b[1][2], b[0][2]), o[0].reversedArray(), arrayOf(g[0][0], g[1][0], g[2][0]), d).run {
                            r[2] = this[0]
                            b[2][2] = this[1][0]
                            b[1][2] = this[1][1]
                            b[0][2] = this[1][2]
                            o[0] = this[2].reversedArray()
                            g[0][0] = this[3][0]
                            g[1][0] = this[3][1]
                            g[2][0] = this[3][2]
                        }
                    }
                    "F+", "F-" -> {
                        if (move == "F+") {
                            r = rotateR(r)
                            d = 0
                        }
                        else {
                            r = rotateL(r)
                            d = 1
                        }
                        changePlain(w[2], b[2], y[0].reversedArray(), g[2], d).run {
                            w[2] = this[0]
                            b[2] = this[1]
                            y[0] = this[2].reversedArray()
                            g[2] = this[3]
                        }
                    }
                    "B+", "B-" -> {
                        if (move == "B+") {
                            o = rotateR(o)
                            d = 0
                        }
                        else {
                            o = rotateL(o)
                            d = 1
                        }
                        changePlain(w[0].reversedArray(), g[0].reversedArray(), y[2], b[0].reversedArray(), d).run {
                            w[0] = this[0].reversedArray()
                            g[0] = this[1].reversedArray()
                            y[2] = this[2]
                            b[0] = this[3].reversedArray()
                        }
                    }
                    "L+", "L-" -> {
                        if (move == "L+") {
                            g = rotateR(g)
                            d = 0
                        }
                        else {
                            g = rotateL(g)
                            d = 1
                        }
                        changePlain(arrayOf(w[0][0], w[1][0], w[2][0]), arrayOf(r[0][0], r[1][0], r[2][0]), arrayOf(y[0][0], y[1][0], y[2][0]), arrayOf(o[0][0], o[1][0], o[2][0]), d).run {
                            w[0][0] = this[0][0]
                            w[1][0] = this[0][1]
                            w[2][0] = this[0][2]

                            r[0][0] = this[1][0]
                            r[1][0] = this[1][1]
                            r[2][0] = this[1][2]

                            y[0][0] = this[2][0]
                            y[1][0] = this[2][1]
                            y[2][0] = this[2][2]

                            o[0][0] = this[3][0]
                            o[1][0] = this[3][1]
                            o[2][0] = this[3][2]
                        }
                    }
                    "R+", "R-" -> {
                        if (move == "R+") {
                            b = rotateR(b)
                            d = 0
                        }
                        else {
                            b = rotateL(b)
                            d = 1
                        }
                        changePlain(arrayOf(w[2][2], w[1][2], w[0][2]), arrayOf(o[2][2], o[1][2], o[0][2]), arrayOf(y[2][2], y[1][2], y[0][2]), arrayOf(r[2][2], r[1][2], r[0][2]), d).run {
                            w[2][2] = this[0][0]
                            w[1][2] = this[0][1]
                            w[0][2] = this[0][2]

                            o[2][2] = this[1][0]
                            o[1][2] = this[1][1]
                            o[0][2] = this[1][2]

                            y[2][2] = this[2][0]
                            y[1][2] = this[2][1]
                            y[0][2] = this[2][2]

                            r[2][2] = this[3][0]
                            r[1][2] = this[3][1]
                            r[0][2] = this[3][2]
                        }
                    }
                }
            }
            w.map { line ->
                line.map {
                    print(it)
                }
                println()
            }
        }
    }

    fun rotateR(t: Array<Array<String>>) =
        arrayOf(
            arrayOf(t[2][0], t[1][0], t[0][0]),
            arrayOf(t[2][1], t[1][1], t[0][1]),
            arrayOf(t[2][2], t[1][2], t[0][2])
        )

    fun rotateL(t: Array<Array<String>>) =
        arrayOf(
            arrayOf(t[0][2], t[1][2], t[2][2]),
            arrayOf(t[0][1], t[1][1], t[2][1]),
            arrayOf(t[0][0], t[1][0], t[2][0])
        )

    fun changePlain(l1: Array<String>, l2: Array<String>, l3: Array<String>, l4: Array<String>, d: Int): Array<Array<String>> {
        return if(d == 1) arrayOf(l2.copyOf(), l3.copyOf(), l4.copyOf(), l1.copyOf())
        else arrayOf(l4.copyOf(), l1.copyOf(), l2.copyOf(), l3.copyOf())
    }
}