package blackjack.domain.card

import blackjack.domain.card.Denomination.ACE
import blackjack.domain.card.Denomination.JACK
import blackjack.domain.card.Denomination.KING
import blackjack.domain.card.Denomination.QUEEN
import blackjack.domain.card.Denomination.THREE
import blackjack.domain.card.Denomination.TWO
import blackjack.domain.card.Suit.SPADE
import io.kotest.core.spec.style.FunSpec

class CardTest : FunSpec({
}) {
    companion object {
        val SPADE_ACE = Card(SPADE, ACE)
        val SPADE_TWO = Card(SPADE, TWO)
        val SPADE_THREE = Card(SPADE, THREE)
        val SPADE_JACK = Card(SPADE, JACK)
        val SPADE_QUEEN = Card(SPADE, QUEEN)
        val SPADE_KING = Card(SPADE, KING)
    }
}
