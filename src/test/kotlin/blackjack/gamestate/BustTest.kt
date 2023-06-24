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

    context("init") {
        test("생성시 카드가 initialhand면 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Bust(Cards()) }
            exception.message shouldBe "2장 미만의 카드로 생성될 수 없다."
        }

        test("생성 시 카드가 bust면 예외가 발생한다.") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> { Bust(Cards.of(SPADE_KING, SPADE_JACK)) }
            exception.message shouldBe "버스트 아닌 카드로 생성될 수 없다."
        }
    }

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
