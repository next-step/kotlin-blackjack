package study

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ExtensionFunctionsTest : FunSpec({
    test("lastChar 확장함수를 사용하여 문자열의 마지막 문자를 가져오는것이 가능하다.") {
        // when
        val result: Char = "kotlin".lastChar()

        // then
        result shouldBe 'n'
    }
})

fun String.lastChar(): Char {
    return this[this.length - 1]
}
