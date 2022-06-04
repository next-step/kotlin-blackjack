package study

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OperatorOverloadingTest : FunSpec({
    test("연산자 오버로딩을 통해 객체사이에 연산자를 사용할 수 있다.") {
        // when
        val result: Point = Point(x = 1, y = 2) + Point(x = 2, y = 3)

        // then
        result shouldBe Point(x = 3, y = 5)
    }
})

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}
