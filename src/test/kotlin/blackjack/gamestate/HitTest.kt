package blackjack.gamestate

import blackjack.CardTest.Companion.SPADE_ACE
import blackjack.CardTest.Companion.SPADE_JACK
import blackjack.CardTest.Companion.SPADE_KING
import blackjack.CardTest.Companion.SPADE_QUEEN
import blackjack.CardTest.Companion.SPADE_THREE
import blackjack.CardTest.Companion.SPADE_TWO
import blackjack.Cards
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.types.shouldBeTypeOf

class HitTest : FunSpec({

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
})
