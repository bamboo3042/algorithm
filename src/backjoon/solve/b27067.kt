package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b27067 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val arr1 = readLine().split(" ").map { it.toInt() }.toMutableList()
        val arr2 = readLine().split(" ").map { it.toInt() }.toMutableList()
        val arr3 = readLine().split(" ").map { it.toInt() }.toMutableList()
        for (i in arr1.indices.reversed()) {
            val n1 = arr1[i]
            val n2 = arr2[i]
            val n3 = arr3[i]
            if (n1 == n2 && n1 == n3) continue
            if (n1 == n2) {
                arr3.remove(n1)
                arr3.add(i, n1)
                println(arr3.joinToString(" "))
                break
            }
            if (n2 == n3) {
                arr1.remove(n2)
                arr1.add(i, n2)
                println(arr1.joinToString(" "))
                break
            }
            if (n1 == n3) {
                arr2.remove(n1)
                arr2.add(i, n1)
                println(arr2.joinToString(" "))
                break
            }
        }
    }
}