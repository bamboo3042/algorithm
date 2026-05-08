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
    val str1 = "A, B, C, D".split(", ")
    val str2 = "P, T, S".split(", ")
    val str3 = "X, Z".split(", ")

    str1.forEach { c1 ->
        str2.forEach { c2 ->
            str3.forEach { c3 ->
                println("$c1$c2$c3,")
            }
        }
    }
}