package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class OperatorOverloadingLearn : StringSpec({

    "연산자 오버로딩(OperatorOver) 을 학습한다" {
        println("참고 : https://kotlinlang.org/docs/operator-overloading.html")
    }

    "연산자 오버로딩 예시" {
        val p1 = Point(0, 1).plus(Point(1, 2))
        val p2 = Point(0, 1) + Point(1, 2)
        p1 shouldBe p2
    }
})

data class Point(val x: Int, val y: Int) {
    //    fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}
