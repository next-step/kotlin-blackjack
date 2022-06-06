package study

fun main() {
    "Kotlin".lastChar()

    1.to("1")
    1 to "1"

    val point = Point(0, 1).plus(Point(1, 2))
    println(point)
}

// 확장함수 - 마치 기존에 있던 클래스에 이 기능이있었던 것 처럼 만들어주는 기능
fun String.lastChar(): Char {
    return get(length - 1) // return this.get(this.length - 1)
}

// 중위연산자 - infix, 확장하려는 함수의 인자가 한개인 경우 마치 영문법처럼 사용할 수 있다.
infix fun Any.to(other: Any) = Pair(this, other)

// 연산자 오버로딩 - operator, 코틀린 연산자를 오버로딩하는 방법
data class Point(val x: Int, val y: Int) {
    fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}
