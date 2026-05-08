//package backjoon.test
//
//class Channer {
//    fun main() {
//
//    }
//
//    class Workflow(
//        private var head: Section?,
//    ) {
//        fun validation(): Boolean {
//            if (this.head == null) return true
//
//            // 각 세션별 최초 1번만 지나가기 위해
//            val visitedIds = mutableSetOf<Long>()
//            val queue = ArrayDeque<CheckNode>()
//
//            queue.add(CheckNode(section = head!!))
//            visitedIds.add(head!!.id)
//
//            while (!queue.isEmpty()) {
//                val current = queue.removeFirst()
//
//                current.section.next.forEach { nextSection ->
//                    if (visitedIds.contains(nextSection.id)) {
//                        if (hasCycle(current, nextSection.id))
//
//                        return@forEach
//                    }
//
//                    //pause 인 경우 이전 경로를 초기화 - 메모리 최적화, 아닐 경우 지나온 경로에 추가
//                    val checkNode = if (current.section.isPause) CheckNode(section = nextSection)
//                    else CheckNode(section = nextSection, parent = current)
//
//                    visitedIds.add(nextSection.id)
//
//                    queue.add(checkNode)
//                }
//            }
//
//            return true
//        }
//
//        fun hasCycle(node: CheckNode?, id: Long): Boolean {
//            var temp = node
//
//            while(temp != null) {
//                if (temp.section.id == id) return true
//
//                temp = temp.parent
//            }
//
//            return false
//        }
//
//    }
//
//    data class CheckNode(
//        val section: Section,
//        val parent: CheckNode? = null,
//    )
//
//    data class Section(
//        val id: Long,
//        val action: String,
//        val next: MutableList<Section> = mutableListOf(),
//    ) {
//        val isPause: Boolean get() = action == "pause"
//    }
//
//    class Workflow2(
//        private var head: Section?,
//    ) {
//        fun validation(): Boolean {
//            if (this.head == null) return true
//
//            // BFS를 위한 큐
//            val queue = ArrayDeque<TraceNode>()
//
//            // 초기 노드 삽입 (부모는 없음: null)
//            queue.add(TraceNode(section = head!!, parent = null))
//
//            // 무한 루프 방지를 위한 전체 방문 횟수 제한 (선택 사항, 안전장치)
//            var count = 0
//            val LIMIT = 100_000 // 적절한 임계값
//
//            while (!queue.isEmpty()) {
//                val current = queue.removeFirst()
//                count++
//                if (count > LIMIT) return false // 너무 오래 돌면 순환으로 간주
//
//                current.section.next.forEach { nextSection ->
//
//                    // 1. 순환 감지: 거슬러 올라가며 확인
//                    if (hasCycle(current, nextSection.id)) {
//                        return false
//                    }
//
//                    // 2. Pause 로직: Pause면 족보(Parent)를 끊음, 아니면 현재를 부모로 연결
//                    // Pause인 경우 parent를 null로 주면 이전 경로 기억이 사라짐
//                    val nextParent = if (current.section.isPause) null else current
//
//                    // 3. 큐에 추가 (Set 복사 없이 가벼운 객체 생성)
//                    queue.add(TraceNode(section = nextSection, parent = nextParent))
//                }
//            }
//
//            return true
//        }
//
//        // 부모를 타고 올라가며 ID가 있는지 확인 (O(경로길이))
//        // Set 조회(O(1))보다 느리지만, 메모리 폭발을 막는 핵심
//        private fun hasCycle(node: TraceNode?, targetId: Long): Boolean {
//            var cursor = node
//            while (cursor != null) {
//                if (cursor.section.id == targetId) {
//                    return true
//                }
//                cursor = cursor.parent
//            }
//            return false
//        }
//    }
//
//    // 가벼운 추적용 노드
//    data class TraceNode(
//        val section: Section,
//        val parent: TraceNode? // 이전 방문 노드를 가리킴 (Linked List 구조)
//    )
//
//    data class Section(
//        val id: Long,
//        val action: String,
//        val next: MutableList<Section> = mutableListOf(),
//    ) {
//        val isPause: Boolean get() = action == "pause"
//    }
//}