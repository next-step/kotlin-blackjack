package blackjack.gamestate

import blackjack.CardTest.Companion.SPADE_ACE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class InitialHandTest : FunSpec({

    context("draw") {
        test("카드를 드로우할 수 있다.") {
            val gameState = InitialHand()

            val actual = gameState.draw(SPADE_ACE) as InitialHand
            actual.cards.values shouldHaveSize 1
            actual.cards.values shouldContain SPADE_ACE
        }
    }

    context("stay") {
        test("카드를 그만받으려하는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { InitialHand().stay() }
            exception.message shouldBe "2장을 받기전에는 카드를 그만받을 수 없다."
        }
    }
})
