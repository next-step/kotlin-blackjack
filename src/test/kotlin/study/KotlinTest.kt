package study

fun main() {
    println("Kotlin".lastChar())
    println(1 to "1")
    println(Point(0, 1) + (Point(1, 2)))
}

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}

infix fun Any.to(other: Any) = Pair(this, other)

fun String.lastChar(): Char {
    return get(length - 1)
}

class KotlinTest
