class Solution {
    private var diceCount = 0
    private var winCount = 0
    private lateinit var answer : IntArray
    private lateinit var DICE: Array<IntArray>
    private lateinit var SELECTED_DICE: BooleanArray

    fun solution(dice: Array<IntArray>): IntArray {
        this.DICE = dice
        this.diceCount = this.DICE.size / 2
        this.answer = IntArray(diceCount)
        this.SELECTED_DICE = BooleanArray(this.DICE.size) { false }
        
        selectDice(0, 0)

        return answer
    }

    fun selectDice(n: Int, count: Int) {
        if (count == diceCount) {
            winCheck()

            return
        }
        if (count > diceCount) return
        if (n == SELECTED_DICE.size) return

        SELECTED_DICE[n] = true
        selectDice(n + 1, count + 1)
        SELECTED_DICE[n] = false
        selectDice(n + 1, count)
    }

    fun winCheck() {
        val list1 = getDiceSumCombination(SELECTED_DICE.mapIndexed { index, b -> if (b) DICE[index] else null }.filterNotNull(), 0, 0).sorted()
        val list2 = getDiceSumCombination(SELECTED_DICE.mapIndexed { index, b -> if (!b) DICE[index] else null }.filterNotNull(), 0, 0).sorted()
        
        var i = 0
        var j = 0
        var count = 0

        while (i < list1.size && j < list2.size) {
            while (j < list2.size && list1[i] > list2[j]) j++
            
            if(j == list2.size) break

            count += if (j > 0 && list1[i] > list2[j-1]) j
            else if (j == 0 && list1[i] > list2[j]) 1
            else 0

            i++
        }
        
        if (j == list1.size && i < list1.size && list1[i] > list2.last()) count += (list1.size - i) * list2.size
        
        if (count > winCount) {
            winCount = count
            answer = SELECTED_DICE.mapIndexed { index, b -> if (b) index + 1 else null }.filterNotNull().toIntArray()
        }
    }

    fun getDiceSumCombination(dice: List<IntArray>, n: Int, sum: Int): List<Int> {
        return if (n == dice.size) listOf(sum)
        else dice[n].map { getDiceSumCombination(dice, n + 1, sum + it) }.flatten()
    }
}