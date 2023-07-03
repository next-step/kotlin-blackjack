package blackjack.domain.gamestate.finished

import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_FIVE
import blackjack.domain.card.CardTest.Companion.SPADE_JACK
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_QUEEN
import blackjack.domain.card.Cards
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
            val exception = shouldThrowExactly<IllegalArgumentException> { Stay(STAY_CARDS).profit(10_000, InitialHand()) }
            exception.message shouldBe "게임이 종료되지 않은 상대와 비교할 수 없다."
        }

        test("버스트와 승부하면 이율이 1이 반환된다.") {
            val actual = Stay(STAY_CARDS).profit(10_000, Bust(BUST_CARDS))
            actual shouldBe 10_000
        }

        test("21보다 가까운 수와 승부하면 이율이 -1이 반환된다.") {
            val source = Stay(Cards.of(SPADE_KING, SPADE_QUEEN))
            val target = Blackjack(Cards.of(SPADE_KING, SPADE_ACE))

            val actual = source.profit(10_000, target)
            actual shouldBe -10_000
        }

        test("21보다 먼 수와 승부하면 이율이 1이 반환된다.") {
            val source = Stay(Cards.of(SPADE_KING, SPADE_QUEEN))
            val target = Stay(Cards.of(SPADE_KING, SPADE_FIVE))

            val actual = source.profit(10_000, target)
            actual shouldBe 10_000
        }

        test("같은 수와 승부하면 이율이 0이 반환된다.") {
            val source = Stay(Cards.of(SPADE_KING, SPADE_QUEEN))
            val target = Stay(Cards.of(SPADE_KING, SPADE_QUEEN))

            val actual = source.profit(10_000, target)
            actual shouldBe 0
        }
    }
}) {
    companion object {
        private val STAY_CARDS = Cards.of(SPADE_KING, SPADE_JACK)
    }
}
