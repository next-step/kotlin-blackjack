package blackjack.domain.player

import blackjack.domain.card.CardTest.Companion.SPADE_FIVE
import blackjack.domain.card.CardTest.Companion.SPADE_JACK
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_THREE
import blackjack.domain.card.CardTest.Companion.SPADE_TWO
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.finished.Bust
import blackjack.domain.gamestate.finished.Stay
import blackjack.domain.gamestate.running.Hit
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeTypeOf

class DealerTest : FunSpec({

    context("draw") {
        test("카드 draw 시 17이 넘어가면 자동으로 Stay된다.") {
            val dealer = Dealer(gameState = Hit(Cards.of(SPADE_TWO, SPADE_KING)))
            dealer.draw(SPADE_FIVE)

            val actual = dealer.gameState
            actual.shouldBeTypeOf<Stay>()
        }

        test("카드 draw 시 21이 넘어가면 busgt된다.") {
            val dealer = Dealer(gameState = Hit(Cards.of(SPADE_TWO, SPADE_KING)))
            dealer.draw(SPADE_JACK)

            val actual = dealer.gameState
            actual.shouldBeTypeOf<Bust>()
        }

        test("카드 draw 시 17이 넘어가지 않으면 계속 Hit다.") {
            val dealer = Dealer(gameState = Hit(Cards.of(SPADE_TWO, SPADE_THREE)))
            dealer.draw(SPADE_FIVE)

            val actual = dealer.gameState
            actual.shouldBeTypeOf<Hit>()
        }
    }
})
