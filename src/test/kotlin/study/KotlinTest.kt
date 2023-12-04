package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class KotlinTest {
    @Test
    fun lastChar() {
//        val actual = lastChar("Kotlin")
        val actual = "Kotlin".lastChar()
        actual shouldBe 'n'
    }

    @Test
    fun plus() {
        val point1 = Point(1, 2)
        val point2 = Point(3, 5)
        
//        val actual = point1.plus(point2)
        val actual = point1 + point2
        actual shouldBe Point(4, 7)
    }
}

// fun lastChar(s:String): Char = s[s.lastIndex]
fun String.lastChar(): Char = this[lastIndex]

data class Point(val x: Int, val y: Int) {
//    fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}

