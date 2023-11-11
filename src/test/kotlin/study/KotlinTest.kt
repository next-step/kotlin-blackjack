package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class KotlinTest {
    @Test
    fun lastChar() {
        val actual = "Kotlin".lastChar()
        actual shouldBe 'n'
    }

    @Test
    fun point() {
        val point1 = Point(1, 2)
        val point2 = Point(3, 5)
        point1 + point2 shouldBe Point(4, 7)
    }
}

fun String.lastChar(): Char = this[length - 1]

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}
