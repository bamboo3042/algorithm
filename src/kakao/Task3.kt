package kakao

class Task3 {
/*
You are given a tree consisting of N nodes, numbered from 0 to N-1. Each node contains one of the letters 'a' or 'b'.
The tree is represented as an array A of length N. A[K] (for K from 0 to N-1) denotes the parent of the K-th node. Node 0 is the root node and does not have a parent, so the value corresponding to it in array A will always be -1. Letters in nodes are represented as a string S. S[K] (for K from 0 to N-1) denotes the letter in the K-th node.
For example, S = "abbab" and A = [-1, 0, 0, 0, 2] represents a tree made of five vertices. It contains following edges: 0 − 1, 0 − 2, 0 − 3, 2 − 4. The picture below illustrates the tree.
Your task is to find the number of vertices on the longest path in the tree, such that no pair of adjacent vertices on the path would contain the same letter.
Write a function: fun solution(S: String, A: IntArray): Int
that, given string S and array A of N integers, returns an integer representing the number of vertices on the longest path in which no two consecutive vertices contain the same letter.
Examples:
1. Given S = "ab" and A = [-1, 0], your function should return 2. The longest path is: 0 − 1. It is marked in red on the picture.
2. Given S = "abbab" and A = [-1, 0, 0, 0, 2], your function should return 3. The longest path is: 1 − 0 − 2. It is marked in red on the picture.
3. Given S = "abab" and A = [-1, 2, 0, 1], your function should return 2. The longest path is: 2 − 1. It is marked in red on the picture.
Write an  efficient algorithm for the following assumptions:
N is an integer within the range [1 .. 40,000];
string S is made only of the characters 'a' and/or 'b';
string S and array A are both of length N;
array A defines a proper tree with a root in node 0.
* */

    private var max = 1
    private lateinit var tree: Array<MutableList<Int>>
    private lateinit var S: String

    fun solution(s: String, a: IntArray): Int {
        // Implement your solution here
        tree = Array(s.length) { mutableListOf() }
        S = s

        for(i in 1 until s.length) tree[a[i]].add(i)

        dfs(0)

        return max
    }

    fun dfs(n: Int): Int {
        if (tree[n].isEmpty()) return 1

        var result = 0
        val arr = IntArray(2) { 0 }

        tree[n].forEach { next ->
            val d = dfs(next)

            if (S[n] == S[next]) return@forEach

            result = maxOf(result, d)

            if (d > arr[1]) {
                arr[0] = arr[1]
                arr[1] = d
            }
            else if (d > arr[0]) arr[0] = d
        }

        max = maxOf(max, arr[0] + 1 + arr[1])

        return result + 1
    }
}