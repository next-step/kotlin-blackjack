package study

fun main() {
    println("kotlin".lastChar())
    println(1 to 1)
    val point = Point(0, 1) + Point(2, 2)
    println(point)
}

/** 연산자 오버로딩 */
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}

/** 중위 표기 */
infix fun Any.to(other: Any) = Pair(this, other)

/** 확장함수 */
fun String.lastChar(): Char {
    return get(length - 1)
}
