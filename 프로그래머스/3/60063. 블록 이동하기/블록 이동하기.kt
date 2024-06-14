class Solution {
    fun solution(board: Array<IntArray>): Int {
        val visited = Array(board.size) { Array(board.size) { BooleanArray(2) { false } } }
        class Robot(
            var y: Int,
            var x: Int,
            var isHorizontal: Boolean,
            var time: Int
        ) {
            fun turnClockwise1(): Robot? {
                return if (isHorizontal) {
                    if (visited[y][x][0] || y > board.size-2 || board[y+1][x] == 1 || board[y+1][x+1] == 1) return null

                    Robot(y, x, false, time+1)
                }
                else {
                    if (x < 1 || visited[y][x-1][1] || board[y][x-1] == 1 || board[y+1][x-1] == 1) return null

                    Robot(y, x-1, true, time+1)
                }
            }

            fun turnCounterClockwise1(): Robot? {
                return if (isHorizontal) {
                    if (y < 1 || visited[y-1][x][0] || board[y-1][x] == 1 || board[y-1][x+1] == 1) return null

                    Robot(y-1, x, false, time+1)
                }
                else {
                    if (x > board.size-2 || visited[y][x][1] || board[y][x+1] == 1 || board[y+1][x+1] == 1) return null

                    Robot(y, x, true, time+1)
                }
            }

            fun turnClockwise2(): Robot? {
                return if (isHorizontal) {
                    if (y < 1 || visited[y-1][x+1][0] || board[y-1][x] == 1 || board[y-1][x+1] == 1) return null

                    Robot(y-1, x+1, false, time+1)
                }
                else {
                    if (x > board.size - 2 || visited[y+1][x][1] || board[y][x+1] == 1 || board[y+1][x+1] == 1) return null

                    Robot(y+1, x, true, time+1)
                }
            }

            fun turnCounterClockwise2(): Robot? {
                return if (isHorizontal) {
                    if (y > board.size - 2 || visited[y][x+1][0] || board[y+1][x] == 1 || board[y+1][x+1] == 1) return null

                    Robot(y, x+1, false, time+1)
                }
                else {
                    if (x < 1 || visited[y+1][x-1][1] || board[y][x-1] == 1 || board[y+1][x-1] == 1) return null

                    Robot(y+1, x-1, true, time+1)
                }
            }

            fun moveUp(): Robot? {
                if (y < 1 || visited[y-1][x][if (isHorizontal) 1 else 0] || board[y-1][x] == 1) return null
                if (isHorizontal && board[y-1][x+1] == 1) return null

                return Robot(y-1, x, isHorizontal, time+1)
            }

            fun moveDown(): Robot? {
                if (isHorizontal) {
                    if (y > board.size-2 || board[y+1][x] == 1 || board[y+1][x+1] == 1) return null
                }
                else {
                    if (y > board.size-3 || board[y+2][x] == 1) return null
                }
                if (visited[y+1][x][if (isHorizontal) 1 else 0]) return null

                return Robot(y+1, x, isHorizontal, time+1)
            }

            fun moveLeft(): Robot? {
                if (x < 1) return null
                if (board[y][x-1] == 1) return null
                if (!isHorizontal && board[y+1][x-1] == 1) return null
                if (visited[y][x-1][if (isHorizontal) 1 else 0]) return null

                return Robot(y, x-1, isHorizontal, time+1)
            }

            fun moveRight(): Robot? {
                if (isHorizontal) {
                    if (x > board.size-3 || board[y][x+2] == 1) return null
                }
                else {
                    if (x > board.size-2 || board[y][x+1] == 1 || board[y+1][x+1] == 1) return null
                }
                if (visited[y][x+1][if (isHorizontal) 1 else 0]) return null

                return Robot(y, x+1, isHorizontal, time+1)
            }
        }

        var answer = 0
        val queue = ArrayDeque<Robot>()

        visited[0][0][1] = true
        queue.add(Robot(0, 0, true, 0))

        fun Robot.addQueue() {
            queue.add(this)
            visited[this.y][this.x][if (this.isHorizontal) 1 else 0] = true
        }

        while (!queue.isEmpty()) {
            val robot = queue.removeFirst()

            if (robot.isHorizontal && robot.x == board.size - 2 && robot.y == board.size - 1) {
                answer = robot.time
                break
            }
            if (!robot.isHorizontal && robot.x == board.size - 1 && robot.y == board.size - 2) {
                answer = robot.time
                break
            }

            robot.turnClockwise1()?.addQueue()
            robot.turnClockwise2()?.addQueue()
            robot.turnCounterClockwise1()?.addQueue()
            robot.turnCounterClockwise2()?.addQueue()
            robot.moveUp()?.addQueue()
            robot.moveDown()?.addQueue()
            robot.moveLeft()?.addQueue()
            robot.moveRight()?.addQueue()
        }

        return answer
    }
}
