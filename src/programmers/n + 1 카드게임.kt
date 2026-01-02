package programmers

class `n + 1 카드게임` {
    class Solution {
        fun solution(coin: Int, cards: IntArray): Int {
            var answer: Int = 0

            val hand = mutableListOf<Int>()
            val trash = mutableListOf<Int>()
            val num = cards.size + 1
            var temp = cards.size / 3
            var c = coin

            repeat(temp) {
                hand.add(cards[it])
            }

            whileLoop@ while (true) {
                answer++

                if(temp == cards.size) break

                repeat(2) { trash.add(cards[temp++]) }

                for (h in hand.toList()) {
                    if (hand.contains(num - h)) {
                        hand.remove(h)
                        hand.remove(num - h)
                        continue@whileLoop
                    }
                }

                if (c > 0) {
                    for (h in hand.toList()) {
                        if (trash.contains(num - h)) {
                            hand.remove(h)
                            trash.remove(num - h)
                            c--
                            continue@whileLoop
                        }
                    }
                }

                if (c > 1) {
                    for (t in trash.toList()) {
                        if (trash.contains(num - t)) {
                            trash.remove(t)
                            trash.remove(num - t)
                            c -= 2
                            continue@whileLoop
                        }
                    }
                }

                break
            }

            return answer
        }
    }
}