package blackjack.domain.gamestate

import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_TWO
import blackjack.domain.card.Cards
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class InitialHandTest : FunSpec({

    context("init") {
        test("상태 생성 시 2장이상인 경우 예외가 발생한다.") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> { InitialHand(Cards.of(SPADE_ACE, SPADE_TWO)) }
            exception.message shouldBe "초기 핸드는 2장 이상 가질 수 없다."
        }
    }

    context("draw") {
        test("카드를 드로우할 수 있다.") {
            val gameState = InitialHand()

            val actual = gameState.draw(SPADE_ACE) as InitialHand
            actual.cards.values shouldHaveSize 1
            actual.cards.values shouldContain SPADE_ACE
        }

        test("카드 드로우 후 2장이 된 경우 Hit 상태가 된다.") {
            val gameState = InitialHand(Cards.of(SPADE_ACE))

            val actual = gameState.draw(SPADE_TWO) as Hit
            actual.cards.values shouldHaveSize 2
            actual.cards.values shouldContainAll listOf(SPADE_ACE, SPADE_TWO)
        }
    }

    context("stay") {
        test("카드를 그만받으려하는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { InitialHand().stay() }
            exception.message shouldBe "2장을 받기전에는 카드를 그만받을 수 없다."
        }
    }
})
