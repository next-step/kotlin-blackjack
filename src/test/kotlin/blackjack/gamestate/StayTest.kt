package blackjack.gamestate

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class StayTest : FunSpec({

    context("stay") {
        test("카드를 그만 받으려하는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { Stay().stay() }
            exception.message shouldBe "종료된 게임은 stay할 수 없다."
        }
    }
})
