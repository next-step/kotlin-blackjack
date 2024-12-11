package step1

object StringUtils {
    fun lastChar(s: String): Char {
        return s.get(s.length - 1)
    }
}

fun String.lastChar(): Char {
    return this.get(this.length - 1)
}

fun Any.to(other: Any) = Pair(this, other)

infix fun Any.to2(other: Any) = Pair(this, other)

data class Point(val x: Int, val y: Int) {
    fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}

data class Point2(val x: Int, val y: Int) {
    operator fun plus(other: Point2): Point2 = Point2(x + other.x, y + other.y)
}
