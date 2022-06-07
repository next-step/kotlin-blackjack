package study

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class InfixNotationTest : FunSpec({
    test("pair라는 중위 표기를 사용해서 Pair 객체를 생성할 수 있다.") {
        // when
        val result = "a" pair "b"

        // then
        result shouldBe Pair("a", "b")
    }
})

infix fun Any.pair(other: Any) = Pair(this, other)
