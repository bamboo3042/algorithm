class Solution {
    private val load = mutableMapOf<Int, MutableList<Int>>()
    private lateinit var visited: BooleanArray

    fun solution(n: Int, edges: Array<IntArray>): Int {
        visited = BooleanArray(n + 1) { false }

        edges.forEach { (a, b) ->
            load.computeIfAbsent(a) { mutableListOf() }.add(b)
            load.computeIfAbsent(b) { mutableListOf() }.add(a)
        }
        
        val start = findLeaf(n)
        
        val (_, list1) = dfs(start)
        
        val (dis2, list2) = dfs(list1[0])
        
        if(list2.size > 1) return dis2
        
        val (dis3, list3) = dfs(list2[0])
        
        return if(list3.size == 1) dis3 - 1
        else dis3
    }
    
    fun dfs(start: Int): Pair<Int, List<Int>> {
        val queue = ArrayDeque<Pair<Int, Int>>()
        var maxD = 0
        var endPoint = mutableListOf<Int>()
        
        visited.fill(false)
        queue.add(start to 0)
        
        while(queue.isNotEmpty()) {
            val (temp, dis) = queue.removeFirst()
            val nextDis = dis + 1
            
            visited[temp] = true
            
            if(dis > maxD) {
                maxD = dis
                endPoint.clear()
                endPoint.add(temp)
            }
            else if(dis == maxD) {
                endPoint.add(temp)
            }
            
            load[temp]!!.forEach { next ->
                if(visited[next]) return@forEach
                
                queue.add(next to nextDis)
            }
        }
        
        return maxD to endPoint
    }
    
    fun findLeaf(size: Int): Int {
        visited = BooleanArray(size + 1) { false }
        visited[1] = true
        val queue = ArrayDeque<Int>()
        queue.add(1)
        
        while(queue.isNotEmpty()) {
            val temp = queue.removeFirst()
            var isNotCheck = true
            
            visited[temp] = true
            
            load[temp]!!.forEach { next ->
                if(visited[next]) return@forEach
                
                isNotCheck = false
                
                queue.addLast(next)
            }
            
            if(isNotCheck) return temp
        }
        
        throw Exception("]-----] Not Reachable [-----[")
    }
}