package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class KotlinTest {

    @Test
    fun kotlinTest() {

        assertAll(
            // extension
            { assertThat("Kotlin".lastChar()).isEqualTo('n') },
            // infix operator
            { assertThat((1 to "1").first).isEqualTo(1) },
            { assertThat((1 to "1").second).isEqualTo("1") },
            // Operator overloading
            { assertThat(Point(0, 1) + Point(1, 2)).isEqualTo(Point(1, 3)) },
            // getter
            { assertThat(listOf("A", "B")[0]).isEqualTo("A") },
        )
    }
}

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}

fun Any.to(other: Any) = Pair(this, other)
fun String.lastChar() = this[this.length - 1]
