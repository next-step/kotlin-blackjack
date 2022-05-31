package study

import org.junit.jupiter.api.Test

infix fun String.and(other: String) = this + other

internal class KotlinTestTest {

    @Test
    internal fun foo() {
        val s = "A" and "B" and "C" and "D"
        println(s)

        val point = Point(1, 2) + Point(3, 4)
        println(point)
    }
}

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}
