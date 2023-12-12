class Solution {

    lateinit var tree: IntArray

    fun solution(stones: IntArray, k: Int): Int {
        var answer = Int.MAX_VALUE
        tree = IntArray(stones.size * 4) { 0 }

        init(stones, 0, stones.size - 1, 1)
        
        repeat(stones.size - k + 1) {      
            val result = find(0, stones.size - 1, 1, it, it+k-1)
            answer = minOf(answer, result)
        }

        return answer
    }

    fun init(stones: IntArray, start: Int, end: Int, n: Int): Int {
        if (start == end) {
            tree[n] = stones[start]
            return tree[n]
        }
        val mid = (start + end) / 2
        tree[n] = maxOf(init(stones, start, mid, n * 2), init(stones, mid+1, end, n * 2 + 1))
        return tree[n]
    }

    fun find(start: Int, end: Int, n: Int, left: Int, right: Int): Int {
        if (left > end || right < start) return 0
        if (left <= start && end <= right) return tree[n]        

        val mid = (start + end) / 2

        return maxOf(find(start, mid, n*2, left, right), find(mid+1, end, n*2+1, left, right))
    }
}