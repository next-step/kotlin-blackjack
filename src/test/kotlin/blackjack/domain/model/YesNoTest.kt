package blackjack.domain.model

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class YesNoTest : FunSpec({
    test("y 또는 n일 경우 정상 반환 테스트") {
        forAll(
            row("y", YesNo.Y),
            row("n", YesNo.N),
        ) { yesNo, answer ->
            YesNo.from(yesNo) shouldBe answer
        }
    }

    test("y/n 가 아닐 경우 null 반환 테스트") {
        val input = "a"
        val result = YesNo.from(input)

        result shouldBe null
    }
})
