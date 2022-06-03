package study

fun main() {

}

data class Point(val x: Int, val y: Int) {
    fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}


infix fun Any.to(other: Any) = Pair(this, other)

fun String.lastChar(): Char {
    return this[this.length - 1]
}


class KotlinTest {
}