package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val actualLastChar = "Kotlin".lastChar()
        assertThat(actualLastChar).isEqualTo('n')
    }

    private fun String.lastChar(): Char {
        return this[this.length - 1]
    }

    @Test
    fun pair() {
        val pair = 1 to "one"

        assertThat(pair.first).isEqualTo(1)
        assertThat(pair.second).isEqualTo("one")
    }

    @Test
    fun operator() {
        val firstPoint = Point(0, 1)
        val secondPoint = Point(1, 0)
        val resultPoint = firstPoint + secondPoint

        assertThat(resultPoint.x).isEqualTo(1)
        assertThat(resultPoint.y).isEqualTo(1)

        assertThat(listOf("a") + listOf("b")).isEqualTo(listOf("a", "b"))
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    }
}
