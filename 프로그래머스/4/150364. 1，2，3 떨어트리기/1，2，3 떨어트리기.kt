class Solution {

    val result = mutableMapOf<Int, MutableList<Int>>()

    fun solution(edges: Array<IntArray>, target: IntArray): IntArray {
        val tree = setTree(edges)

        for (i in target.indices) if (target[i] != 0) result[i] = mutableListOf()

        findList(tree, target)
        
        val answer = IntArray(result.values.sumOf { it.size }) { 0 }

        result.forEach { (t, list) ->
            list.forEachIndexed { index, i ->
                val n = target[t] - (list.size - index - 1) * 3
                if (n > 0) {
                    answer[i] = n
                    target[t] -= n
                }
                else {
                    answer[i] = 1
                    target[t] -= 1
                }
                
                if (target[t] < 0) return intArrayOf(-1)
            }
        }

        return answer
    }

    fun findList(tree: List<Tree>, target: IntArray) {
        var count = 0
        while (result.map { (k, v) -> target[k] > v.size * 3 }.contains(true)) {
            result[drop(tree)]!!.add(count++)
        }
    }

    fun setTree(edges: Array<IntArray>): List<Tree> {
        val tree = List (edges.size + 1) { Tree(it, null, mutableListOf()) }
        edges.map { (a, b) ->
            tree[a-1].child.add(b-1)
            if (tree[a-1].nextIndex == null || tree[a-1].nextIndex!! > b-1) tree[a-1].nextIndex = b-1
        }
        tree.forEach {
            it.child.sort()
            if (it.nextIndex != null) it.nextIndex = it.child.indexOf(it.nextIndex)
        }

        return tree
    }

    fun drop(tree: List<Tree>): Int {
        var temp = 0
        while (tree[temp].nextIndex != null) {
            val t = tree[temp]
            temp = t.child[t.nextIndex!!]
            t.drop()
        }

        return temp
    }

    data class Tree(
        val index: Int,
        var nextIndex: Int?,
        val child: MutableList<Int>
    ) {
        fun drop() {
            if (nextIndex != null) {
                nextIndex = if (nextIndex == child.size - 1) 0 else nextIndex!! + 1
            }
        }
    }
}