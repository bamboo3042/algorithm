package programmers

class 전화번호_목록 {
    class Solution {
        fun solution(phone_book: Array<String>): Boolean {
            phone_book.sort()
            for (i in 0 until phone_book.size - 1) {
                if (phone_book[i+1].startsWith(phone_book[i])) return false
            }
            return true
        }
    }
}