package study

fun main() {
    "Kotlin".lastChar() // 'n'

    println(1 to "1")

    Point(0, 1) + Point(2, 3)
}

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}

fun String.lastChar(): Char = get(length - 1)

infix fun Any.to(other: Any) = Pair(this, other)
