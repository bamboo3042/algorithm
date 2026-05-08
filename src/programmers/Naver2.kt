package programmers

class Naver2 {
    fun solution(d: IntArray, t: Array<String>): Int {
        val TRUCKS = "PGM"

        val lastIndexMap = TRUCKS.associateWith { 0 }.toMutableMap()
        val trashMap = TRUCKS.associateWith { 0 }.toMutableMap()

        t.forEachIndexed { i, s ->
            s.forEach { c ->
                lastIndexMap[c] = i
                trashMap[c] = trashMap.getOrDefault(c, 0) + 1
            }
        }

        return TRUCKS.fold(0) { acc, truck ->
            if (trashMap[truck] == 0) acc

            var roadTime = 0

            for (i in 0 .. lastIndexMap[truck]!!) {
                roadTime += d[i]
            }

            val totalTime = roadTime * 2 + trashMap[truck]!!

            maxOf(acc, totalTime)
        }
    }

    fun solution2(d: IntArray, t: Array<String>): Int {
        val trashMap = t.map { s ->
            s.groupBy { it }.mapValues { it.value.size }
        }

        return "PGM".fold(0) { acc, truck ->
            var roadTime = 0
            var trashCount = 0
            var tempTime = 0

            for ((index, dis) in d.withIndex()) {
                tempTime += dis

                if (trashMap[index].contains(truck)) {
                    roadTime += tempTime
                    trashCount += trashMap[index][truck]!!
                    tempTime = 0
                }
            }

            val totalTime = roadTime * 2 + trashCount

            maxOf(acc, totalTime)
        }
    }
}