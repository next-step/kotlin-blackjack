package dsl

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class KotlinTest {
    @Test
    fun stringTest() {
        assertThat("Kotlin".lastChar()).isEqualTo('n')
    }

    @Test
    fun infix() {
        1 to "1"
    }

    @Test
    fun operator() {
        val actual = Point(0, 1) + Point(1, 2)
        actual shouldBe Point(1, 3)
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    }
}

fun String.lastChar(): Char {
    return get(length - 1)
}
