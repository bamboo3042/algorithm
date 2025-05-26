package backjoon

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageSlice {
    fun slice() {
        // 입력 이미지 폴더 경로
        val inputFolderPath = "/Users/jeong-yeonghun/Downloads/kakao"
        // 출력 디렉토리
        val outputFolderPath = "/Users/jeong-yeonghun/Downloads/newKakao"
        File(outputFolderPath).mkdirs()

        // 입력 폴더에서 모든 이미지 파일 가져오기
        val inputFolder = File(inputFolderPath)
        val imageFiles = inputFolder.listFiles { _, name ->
            name.endsWith(".jpg", ignoreCase = true) || name.endsWith(".png", ignoreCase = true) || name.endsWith(".jpeg", ignoreCase = true)
        } ?: arrayOf()

        for (imageFile in imageFiles) {
            val originalImage: BufferedImage = ImageIO.read(imageFile)
            val baseName = imageFile.nameWithoutExtension
//            val imageOutputFolder = File("$outputFolderPath/$baseName")
//            imageOutputFolder.mkdirs()
            val imageOutputFolder = File("$outputFolderPath")

            // 최대 세로 길이 2000 이하로 나누기
            splitImageWithMaxHeight(originalImage, 2000, imageOutputFolder, baseName)
        }
    }

    fun splitImageWithMaxHeight(
        image: BufferedImage,
        maxSliceHeight: Int,
        outputFolder: File,
        baseName: String
    ) {
        val width = image.width
        val height = image.height

        // 조각 개수 계산: 높이를 최대 세로 길이로 나눈다.
        val divisions = (height + maxSliceHeight - 1) / maxSliceHeight // 올림 계산
        val sliceHeight = (height + divisions - 1) / divisions // 각 조각 높이 조정

        var startY = 0

        for (i in 0 until divisions) {
            val endY = if (startY + sliceHeight > height) height else startY + sliceHeight
            val subImage = image.getSubimage(0, startY, width, endY - startY)
            val outputFile = File(outputFolder, "${baseName}_$i.jpg")
            ImageIO.write(subImage, "jpg", outputFile)
            println("Saved: ${outputFile.absolutePath}")
            startY += sliceHeight
        }
    }
}