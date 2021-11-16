package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val lastChar = "Kotlin".lastChar()
        assertThat(lastChar).isEqualTo('n')
    }

    private fun String.lastChar(): Char {
        return this[this.length - 1]
    }

    @Test
    fun pair() {
        val pair = 1 to "one"
    }

    @Test
    fun operator() {
        val point = Point(0, 1) + (Point(1, 2))
        assertThat(point).isEqualTo(Point(1, 3))
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    }
}
