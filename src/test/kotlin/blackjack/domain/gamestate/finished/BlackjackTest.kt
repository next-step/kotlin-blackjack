package blackjack.domain.gamestate.finished

import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_JACK
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_QUEEN
import blackjack.domain.card.CardTest.Companion.SPADE_TWO
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.running.Hit
import blackjack.domain.gamestate.running.InitialHand
import blackjack.domain.player.Money
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import java.lang.IllegalStateException

class BlackjackTest : FunSpec({

    context("init") {
        test("생성 시 카드가 initialhand면 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Blackjack(Cards()) }
            exception.message shouldBe "2장 미만의 카드로 생성될 수 없습니다."
        }

        test("생성 시 카드 점수가 21이 아니면 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Blackjack(Cards.of(SPADE_KING, SPADE_TWO)) }
            exception.message shouldBe "21인 카드만 블랙잭이 될 수 있습니다."
        }
    }

    context("isBust") {
        test("blackjack은 bust가 아니다.") {
            val actual = Blackjack(Cards.of(SPADE_KING, SPADE_ACE)).isBust()
            actual shouldBe false
        }
    }

    context("score") {
        test("blackjack은 점수가 21이다.") {
            val actual = Blackjack(Cards.of(SPADE_KING, SPADE_ACE)).score()
            actual shouldBe 21
        }
    }

    context("profit") {
        test("게임이 종료되지 않은 상대와 승부하면 예외가 발생한다.") {
            val source = Blackjack(Cards.of(SPADE_KING, SPADE_ACE))
            val target = InitialHand()

            val exception = shouldThrowExactly<IllegalArgumentException> { source.profit(Money(10_000), target) }
            exception.message shouldBe "게임이 종료되지 않은 상대와 비교할 수 없습니다."
        }

        test("blackjack과 승부하면 이율이 0이 반환된다.") {
            val source = Blackjack(Cards.of(SPADE_KING, SPADE_ACE))
            val target = Blackjack(Cards.of(SPADE_KING, SPADE_ACE))

            val actual = source.profit(Money(10_000), target)
            actual shouldBe 0
        }

        forAll(
            row(Stay(Cards.of(SPADE_KING, SPADE_QUEEN))),
            row(Bust(Cards.of(SPADE_KING, SPADE_QUEEN, SPADE_JACK))),
        ) { input ->
            test("다른 상태와 승부하면 이율이 1.5가 반환된다.") {
                val source = Blackjack(Cards.of(SPADE_KING, SPADE_ACE))

                val actual = source.profit(Money(10_000), input)
                actual shouldBe 15_000
            }
        }
    }
})
