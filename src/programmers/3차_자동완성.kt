package programmers

class `3차_자동완성` {
    class Solution {

        private val head = Node()

        fun solution(words: Array<String>): Int {
            var answer = 0
            for (word in words) makeTree(word)
            for (word in words) answer += countWord(word)
            return answer
        }

        data class Node(
            val list: MutableMap<Char, Node> = mutableMapOf(),
            var word: String = ""
        )

        private fun makeTree(word: String) {
            var temp: Node = head

            for (i in word.indices) {
                val next = temp.list[word[i]]

                if (next == null) {
                    temp.list[word[i]] = Node(mutableMapOf(), word)
                    temp = temp.list[word[i]]!!
                }
                else {
                    temp = next
                    temp.word = word.substring(0, i)
                }
            }
        }

        private fun countWord(word: String): Int {
            var c = 0
            var temp = head

            for (i in word.indices) {
                if (temp.word == word) break

                c++
                temp = head.list[word[i]]!!
            }

            return c
        }
    }
}