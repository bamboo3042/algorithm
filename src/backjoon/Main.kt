package backjoon

import java.security.SecureRandom
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.Base64
import kotlin.random.Random

fun main(args: Array<String>) {

    val list = listOf(14, 0, 11, 15, 0, 8, -3, 13)

    repeat(26) { r ->
        val str = StringBuilder()

        list.forEach { l ->
            val c = (l + 'a'.code + r).toChar()

            if (c !in 'a' .. 'z') return@repeat

            str.append(c)
        }

        println(str.toString())
    }
}