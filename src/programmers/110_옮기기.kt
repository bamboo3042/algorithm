package programmers

class `110_옮기기` {
    class Solution {
        fun solution(s: Array<String>): Array<String> {
            return s.map {
                if (it.length <= 3) return@map it

                val queue = ArrayDeque<Char> ()
                var count = 0
                val result = StringBuilder()

                it.reversed().forEach {
                    queue.add(it)

                    if (queue.size >= 3) {
                        val i = queue.lastIndex
                        if (queue[i] == '1' && queue[i-1] == '1' && queue[i-2] == '0') {
                            count++

                            repeat(3) { queue.removeLast() }
                        }
                    }
                }

                while (queue.isNotEmpty()) {
                    if ((queue.size >= 2 && queue.last() == '1' && queue[queue.lastIndex-1] == '1') || (queue.size == 1 && queue.last() == '1')) {
                        while(count-- > 0) { result.append("110") }
                    }
                    result.append(queue.removeLast())
                }

                while(count-- > 0) { result.append("110") }

                result.toString()
            }.toTypedArray()
        }
    }
}