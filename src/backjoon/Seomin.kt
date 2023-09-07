package backjoon

import java.io.File

class Seomin {
    fun main() {
        val fileNumbers = (2 .. 4)
        fileNumbers.forEach { fileNumber ->
            val fileName = "$fileNumber.txt"
            val inputFilePath = "/Users/jeong-yeonghun/Desktop/algorithm/src/backjoon/$fileName" // 입력할 txt 파일 경로
            val outputFilePath = "/Users/jeong-yeonghun/Desktop/algorithm/src/backjoon/result/$fileName" // 결과를 저장할 txt 파일 경로

            val inputFile = File(inputFilePath)
            val outputFile = File(outputFilePath)

            if (!inputFile.exists()) {
                println("입력 파일이 존재하지 않습니다.")
                return
            }

            val words = mutableListOf<String>()

            inputFile.forEachLine { line ->
                val wordRegex = Regex("""\b[A-Za-z]+\b""")
                val matches = wordRegex.findAll(line)
                for (match in matches) {
                    val word = match.value
                    println(word)
                    words.add(word)
                }
            }

            if (words.isEmpty()) {
                println("파일에 알파벳으로만 된 단어가 없습니다.")
                return
            }

            val modifiedWords = words.map { word ->
                if (word.all { it.isLetter() }) {
                    word.toUpperCase()
                } else {
                    word
                }
            }

            outputFile.bufferedWriter().use { writer -> modifiedWords.forEach { word -> writer.write(word) } }

            println("변경된 단어가 저장되었습니다. $outputFilePath")
        }
    }

}