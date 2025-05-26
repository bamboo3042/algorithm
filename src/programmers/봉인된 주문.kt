package programmers

class `봉인된 주문` {
    class Solution {
        fun solution(n: Long, bans: Array<String>): String {
            val newBans = bans.sortedWith(compareBy({it.toLong()}))
            var temp = n
            var count = 0

            while (count != newBans.size && newBans[count].toLong() <= temp) {
                count++
                temp++
            }

            return temp.toStr()
        }

        private fun Long.toStr(): String {
            var result = ""
            var n = this

            while (n > 0) {
                val mod = (n - 1) % 26
                result = (mod + 'a'.code).toInt().toChar() + result
                n = (n - 1) / 26
            }

            return result
        }

        private fun String.toLong(): Long {
            var number = 0L

            this.reversed().forEachIndexed { i, c ->
                number += (c - 'a' + 1) * (26L.pow(i))
            }

            return number
        }

        private fun Long.pow(exp: Int): Long {
            var result = 1L
            repeat(exp) { result *= this }
            return result
        }
    }
}