class Solution {

    lateinit var topX: IntArray
    lateinit var visited: BooleanArray
    val blockMap: MutableMap<Int, MutableList<Pair<Int, Int>>> = mutableMapOf()
    val answerSet = mutableSetOf<Int>()
    var prevBlockNumber = 0

    fun solution(board: Array<IntArray>): Int {
        topX = IntArray(board.size) { Int.MAX_VALUE }
        visited = BooleanArray(201) { false }
        visited[0] = true
        answerSet.add(0)

        for (x in board.indices) {
            for (y in board.indices) {
                if (board[x][y] != 0) blockMap.computeIfAbsent(board[x][y]) { mutableListOf() }.add(x to y)
            }
        }

        for (x in board.indices) {
            for (y in board.indices) {
                if (!visited[board[x][y]]) removeBlock(board, x, y)
            }
        }

        return answerSet.size - 1
    }

    fun removeBlock(board: Array<IntArray>, x: Int, y: Int) {
        val blockNumber = board[x][y]
        val block = blockMap[blockNumber]!!
        val shape = findShape(block)

        var removeCheck = shape.isRemovable
        
        visited[blockNumber] = true
        
        if (removeCheck) {
            removeCheck = when(shape) {
                Shape.R3 -> (topX[y+1] > x && answerSet.contains(board[x][y+1])) && (topX[y+2] > x && answerSet.contains(board[x][y+2]))
                Shape.R4 -> topX[y-1] > x && answerSet.contains(board[x+1][y-1])
                Shape.Y2 -> topX[y+1] > x && answerSet.contains(board[x][y+1]) && answerSet.contains(board[x+1][y+1])
                Shape.Y3 -> topX[y-1] > x && topX[y-2] > x
                Shape.B1 -> topX[y-1] > x && topX[y+1] > x && answerSet.contains(board[x][y+1])
                else -> false
            }
        }

        if (removeCheck) {
            answerSet.add(blockNumber)

            if (shape == Shape.R3 || shape == Shape.B1 || shape == Shape.Y3) prevBlockRemove(board)
        }
        else {
            block.forEach { (bx, by) -> topX[by] = minOf(topX[by], bx) }

            if (shape == Shape.Y2) prevBlockNumber = blockNumber
            else prevBlockNumber = 0
        }
    }

    fun prevBlockRemove(board: Array<IntArray>) {
        if (prevBlockNumber == 0) return

        val block = blockMap[prevBlockNumber]!!

        val (bx, by) = block[0]

        if (topX[by+1] == bx + 2) {
            answerSet.add(prevBlockNumber)

            if (topX[by] == bx) topX[by] = Int.MAX_VALUE
            topX[by+1] = Int.MAX_VALUE
        }

        prevBlockNumber = 0
    }

    fun findShape(block: MutableList<Pair<Int, Int>>): Shape {
        return when {
            block[0].first == block[1].first -> {
                if (block[2].first == block[0].first) {
                    if (block[3].second == block[0].second) Shape.Y1
                    else if (block[3].second == block[0].second + 1) Shape.B3
                    else Shape.R1
                }
                else {
                    if (block[2].second ==block[0].second) Shape.R2
                    else Shape.Y4
                }
            }
            block[1].second + 1 == block[0].second -> {
                if (block[3].first == block[1].first) Shape.B1
                else Shape.B4
            }
            block[1].second == block[0].second -> {
                if (block[2].first == block[1].first) {
                    if (block[3].first == block[1].first) Shape.R3
                    else Shape.B2
                }
                else {
                    if (block[2].second + 1 == block[0].second) Shape.R4
                    else Shape.Y2
                }
            }
            else -> Shape.Y3
        }
    }

    enum class Shape(val isRemovable: Boolean) {
        R1(false),
        R2(false),
        R3(true),
        R4(true),
        Y1(false),
        Y2(true),
        Y3(true),
        Y4(false),
        B1(true),
        B2(false),
        B3(false),
        B4(false);
    }
}