package blackjack.gamestate

import blackjack.CardTest.Companion.SPADE_ACE
import blackjack.CardTest.Companion.SPADE_JACK
import blackjack.CardTest.Companion.SPADE_KING
import blackjack.CardTest.Companion.SPADE_QUEEN
import blackjack.Cards
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BustTest : FunSpec({

    context("draw") {
        test("카드를 드로우하려는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { Bust(BUST_CARDS).draw(SPADE_ACE) }
            exception.message shouldBe "종료된 게임은 draw할 수 없다."
        }
    }

    context("stay") {
        test("카드를 그만 받으려하는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { Bust(BUST_CARDS).stay() }
            exception.message shouldBe "종료된 게임은 stay할 수 없다."
        }
    }
}) {
    companion object {
        private val BUST_CARDS = Cards.of(SPADE_KING, SPADE_QUEEN, SPADE_JACK)
    }
}
