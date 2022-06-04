package study

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class LambdaWithReceiverTest : FunSpec({
    test("수신객체 지정 람다 학습 테스트") {
        // given
        val sut: StringBuilder = StringBuilder()

        // when
        val result: StringBuilder = sut.apply {
            this.append("Yes")
            append("No")
        }

        // then
        result.toString() shouldBe "YesNo"
    }
})
