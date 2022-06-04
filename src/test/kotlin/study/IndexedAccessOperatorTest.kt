package study

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class IndexedAccessOperatorTest : FunSpec({
    test("인덱스를 통한 접근은 '[0]'같은 방식으로도 가능하다.") {
        // given
        val sut: List<Int> = listOf(1, 2, 3)

        // when
        val result = sut[0]

        // then
        result shouldBe 1
    }
})
