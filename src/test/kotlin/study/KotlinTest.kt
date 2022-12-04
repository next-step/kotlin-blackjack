package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class KotlinTest {

    fun String.lastChar(): Char {
        return this[this.length - 1]
    }

    @Test
    fun name() {
        "Kotlin".lastChar() shouldBe 'n'
    }

    infix fun Any.to(other: Any) = Pair(this, other)

    @Test
    fun infix() {
        1 to "one" shouldBe Pair(1, "one")
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    }

    @Test
    fun operator() {
        val actual = Point(0, 1) + Point(1, 2)
        actual shouldBe Point(1, 3)
    }
}
