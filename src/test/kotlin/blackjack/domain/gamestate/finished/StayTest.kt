package blackjack.domain.gamestate.finished

import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_JACK
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_QUEEN
import blackjack.domain.card.CardTest.Companion.SPADE_THREE
import blackjack.domain.card.CardTest.Companion.SPADE_TWO
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.Competition
import blackjack.domain.gamestate.finished.BustTest.Companion.BUST_CARDS
import blackjack.domain.gamestate.running.InitialHand
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class StayTest : FunSpec({

    context("init") {
        test("생성시 카드가 initialhand면 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Stay(Cards()) }
            exception.message shouldBe "2장 미만의 카드로 생성될 수 없다."
        }

        test("생성 시 카드가 bust면 예외가 발생한다.") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> { Stay(Cards.of(SPADE_KING, SPADE_JACK, SPADE_QUEEN)) }
            exception.message shouldBe "버스트 카드로 생성될 수 없다."
        }
    }

    context("draw") {
        test("카드를 드로우하려는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { Stay(STAY_CARDS).draw(SPADE_ACE) }
            exception.message shouldBe "종료된 게임은 draw할 수 없다."
        }
    }

    context("stay") {
        test("카드를 그만 받으려하는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { Stay(STAY_CARDS).stay() }
            exception.message shouldBe "종료된 게임은 stay할 수 없다."
        }
    }

    context("isBust") {
        test("bust인지 확인한다") {
            val actual = Stay(STAY_CARDS).isBust()
            actual shouldBe false
        }
    }

    context("isFinished") {
        test("finished인지 확인한다.") {
            val actual = Stay(STAY_CARDS).isFinished()
            actual shouldBe true
        }
    }

    context("compete") {
        test("종료되지 않은 상대와 경쟁할 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Stay(STAY_CARDS).compete(InitialHand()) }
            exception.message shouldBe "게임이 종료되지 않은 상대와 비교할 수 없다."
        }

        test("상대가 bust면 승리한다.") {
            val actual = Stay(STAY_CARDS).compete(Bust(BUST_CARDS))
            actual shouldBe Competition.WIN
        }

        test("상대보다 21에 멀면 패배한다.") {
            val actual = Stay(Cards.of(SPADE_TWO, SPADE_THREE)).compete(Stay(Cards.of(SPADE_ACE, SPADE_TWO)))
            actual shouldBe Competition.LOSE
        }

        test("상대보다 21에 가까우면 승리한다.") {
            val actual = Stay(Cards.of(SPADE_ACE, SPADE_TWO)).compete(Stay(Cards.of(SPADE_TWO, SPADE_THREE)))
            actual shouldBe Competition.WIN
        }

        test("상대와 점수가 같으면 무승부한다.") {
            val actual = Stay(Cards.of(SPADE_ACE, SPADE_TWO)).compete(Stay(Cards.of(SPADE_ACE, SPADE_TWO)))
            actual shouldBe Competition.DRAW
        }
    }
}) {
    companion object {
        private val STAY_CARDS = Cards.of(SPADE_KING, SPADE_JACK)
    }
}
