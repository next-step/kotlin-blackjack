package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class KotlinTest {

    @Test
    fun extension() {
        "Kotlin".lastChar()
    }

    @Test
    fun infix() {
        1 to "one"
    }

    @Test
    fun operator() {
        val actual = Point(0, 1) + (Point(1, 2))

        actual shouldBe Point(1, 3)
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    }
}

fun String.lastChar(): Char {
    return get(length - 1)
}
