package blackjack.gamestate

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class InitialHandTest : FunSpec({

    context("stay") {
        test("카드를 그만받으려하는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { InitialHand().stay() }
            exception.message shouldBe "2장을 받기전에는 카드를 그만받을 수 없다."
        }
    }
})
