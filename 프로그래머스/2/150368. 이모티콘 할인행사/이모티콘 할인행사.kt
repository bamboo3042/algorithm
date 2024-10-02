class Solution {
    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        val answer = intArrayOf(0, 0)

        val ratios = intArrayOf(10, 20, 30, 40)
        val emoticonPrices = emoticons.map { price -> ratios.map { ratio -> price - (price * ratio / 100) } }
        val emoticonRatios = IntArray(emoticons.size) { 0 }

        fun calculate() {
            var count = 0
            var totalPrice = 0

            users.forEach { (userRatio, minPrice) ->
                var userPrice = 0

                emoticonRatios.forEachIndexed { index,  emoticonRatioIndex ->
                    val ratio = ratios[emoticonRatioIndex]

                    if (ratio >= userRatio) userPrice += emoticonPrices[index][emoticonRatioIndex]
                }

                if (userPrice >= minPrice) count++
                else totalPrice += userPrice
            }

            if (count > answer[0] || (count == answer[0] && totalPrice > answer[1])) {
                answer[0] = count
                answer[1] = totalPrice
            }
        }

        fun backTracking(n: Int) {
            if (n == emoticons.size) {
                calculate()
                return
            }

            val prev = emoticonRatios[n]

            for (i in 0 until 4) {
                emoticonRatios[n] = i
                backTracking(n + 1)
                emoticonRatios[n] = prev
            }
        }

        backTracking(0)

        return answer
    }
}