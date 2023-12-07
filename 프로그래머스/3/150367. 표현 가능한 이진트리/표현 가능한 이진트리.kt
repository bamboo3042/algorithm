class Solution {
    fun solution(numbers: LongArray): IntArray {
        return numbers.mapIndexed { index, l ->
            val str = addPrefix(l.toString(2))
            
            if (search(str, 0, str.length, true)) 1 
            else 0
        }.toIntArray()
    }

    fun addPrefix(binaryStr: String): String {
        var n = 1
        var str = binaryStr
        while (n*2 <= str.length) n *= 2

        n *= 2
        n--
        while (n > str.length) str = "0$str"

        return str
    }

    fun search(str: String, start: Int, end: Int, isOne: Boolean): Boolean {
        if (start >= str.length) return true
        if (start >= end) return str[start] == '0' || isOne
        
        val mid = (start + end) / 2
        
        return if (str[mid] == '1' && !isOne) false
        else search(str, start, mid-1, str[mid] == '1') && search(str, mid+1, end, str[mid] == '1')
    }
}
