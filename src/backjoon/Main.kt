package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

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

        }
    }
    if (arr1.last() == arr2.last()) {
        arr3.remove(arr1.last())
        arr3.add(arr1.last())
        println(arr3.joinToString(" "))
    }
    else if (arr1.last() == arr3.last()) {
        arr2.remove(arr1.last())
        arr2.add(arr1.last())
        println(arr2.joinToString(" "))
    }
    else {
        arr1.remove(arr2.last())
        arr1.add(arr2.last())
        println(arr1.joinToString(" "))
    }
}