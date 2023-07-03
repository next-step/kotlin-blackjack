package blackjack.domain.gamestate.finished

import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_JACK
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_QUEEN
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.running.InitialHand
import blackjack.domain.player.Money
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BustTest : FunSpec({

    context("init") {
        test("생성시 카드가 initialhand면 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Bust(Cards()) }
            exception.message shouldBe "2장 미만의 카드로 생성될 수 없습니다."
        }

        test("생성 시 카드가 bust가 아니면 예외가 발생한다.") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> { Bust(Cards.of(SPADE_KING, SPADE_JACK)) }
            exception.message shouldBe "버스트 아닌 카드로 생성될 수 없다."
        }
    }

    context("draw") {
        test("카드를 드로우하려는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { Bust(BUST_CARDS).draw(SPADE_ACE) }
            exception.message shouldBe "종료된 게임은 드로우할 수 없습니다."
        }
    }

    context("stay") {
        test("카드를 그만 받으려하는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { Bust(BUST_CARDS).stay() }
            exception.message shouldBe "종료된 게임은 stay할 수 없습니다."
        }
    }

    context("isBust") {
        test("bust인지 확인한다") {
            val actual = Bust(BUST_CARDS).isBust()
            actual shouldBe true
        }
    }

    context("isFinished") {
        test("finished인지 확인한다.") {
            val actual = Bust(BUST_CARDS).isFinished()
            actual shouldBe true
        }
    }

    context("profit") {
        test("종료되지 않은 상대와 경쟁할 경우 예외가 발생한다.") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> { Bust(BUST_CARDS).profit(Money(10_000), InitialHand()) }
            exception.message shouldBe "게임이 종료되지 않은 상대와 비교할 수 없습니다."
        }

        test("버스트와 승부하면 이율이 0이 반환된다.") {
            val source = Bust(BUST_CARDS)
            val target = Bust(BUST_CARDS)

            val actual = source.profit(Money(10_000), target)
            actual shouldBe 0
        }

        test("다른 상대와 승부하면 이율이 -1이 반환된다.") {
            val source = Bust(BUST_CARDS)
            val target = Stay(Cards.of(SPADE_KING, SPADE_QUEEN))

            val actual = source.profit(Money(10_000), target)
            actual shouldBe -10_000
        }
    }
}) {
    companion object {
        val BUST_CARDS = Cards.of(SPADE_KING, SPADE_QUEEN, SPADE_JACK)
    }
}
