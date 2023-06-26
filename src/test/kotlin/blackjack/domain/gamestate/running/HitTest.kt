package blackjack.domain.gamestate.running

import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_JACK
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_QUEEN
import blackjack.domain.card.CardTest.Companion.SPADE_THREE
import blackjack.domain.card.CardTest.Companion.SPADE_TWO
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.finished.Bust
import blackjack.domain.gamestate.finished.BustTest.Companion.BUST_CARDS
import blackjack.domain.gamestate.finished.Stay
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class HitTest : FunSpec({

    context("init") {
        test("생성시 카드가 initialhand면 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Hit(Cards()) }
            exception.message shouldBe "2장 미만의 카드로 생성될 수 없다."
        }

        test("생성 시 카드가 bust면 예외가 발생한다.") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> { Hit(Cards.of(SPADE_KING, SPADE_JACK, SPADE_QUEEN)) }
            exception.message shouldBe "버스트 카드로 생성될 수 없다."
        }
    }

    context("draw") {
        test("카드를 드로우받는다.") {
            val hit = Hit(Cards.of(SPADE_ACE, SPADE_TWO))
            val actual = hit.draw(SPADE_THREE)

            actual.shouldBeTypeOf<Hit>()
            actual.cards.values shouldHaveSize 3
            actual.cards.values shouldContainAll listOf(SPADE_ACE, SPADE_TWO, SPADE_THREE)
        }

        test("드로우 후 버스트면 Bust로 변경한다.") {
            val hit = Hit(Cards.of(SPADE_KING, SPADE_JACK))
            val actual = hit.draw(SPADE_QUEEN)

            actual.shouldBeTypeOf<Bust>()
            actual.cards.values shouldHaveSize 3
            actual.cards.values shouldContainAll listOf(SPADE_KING, SPADE_JACK, SPADE_QUEEN)
        }
    }

    context("stay") {
        test("stay 상태로 변경한다.") {
            val actual = Hit(Cards.of(SPADE_ACE, SPADE_TWO)).stay()
            actual.shouldBeTypeOf<Stay>()
        }
    }

    context("isBust") {
        test("bust인지 확인한다") {
            val actual = Hit(Cards.of(SPADE_KING, SPADE_JACK)).isBust()
            actual shouldBe false
        }
    }

    context("score") {
        test("스코어를 계산하려는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { Hit(Cards.of(SPADE_KING, SPADE_JACK)).score() }
            exception.message shouldBe "턴이 종료되지 않아 점수를 반환할 수 없다."
        }
    }

    context("compete") {
        test("승패를 계산하려하는 경우 예외가 발생한다") {
            val exception = shouldThrowExactly<IllegalStateException> {
                Hit(Cards.of(SPADE_KING, SPADE_JACK)).compete(Bust(BUST_CARDS))
            }
            exception.message shouldBe "턴이 종료되지 않아 승부를 가릴 수 없다."
        }
    }
})
