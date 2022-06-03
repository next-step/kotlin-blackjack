package study

fun main() {
    "kotlin".lastChar()

    println(1 to 1)

    val point = Point(0, 1) + Point(2, 2)
    println(point)
}

fun String.lastChar(): Char {
    return get(length - 1)
}

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}

infix fun Any.to(other: Any) = Pair(this, other)
