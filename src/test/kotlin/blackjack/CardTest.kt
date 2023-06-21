package blackjack

import blackjack.Denomination.ACE
import blackjack.Denomination.JACK
import blackjack.Denomination.KING
import blackjack.Denomination.QUEEN
import blackjack.Denomination.TWO
import blackjack.Suit.SPADE
import io.kotest.core.spec.style.FunSpec

class CardTest : FunSpec({
}) {
    companion object {
        val SPADE_ACE = Card(SPADE, ACE)
        val SPADE_TWO = Card(SPADE, TWO)
        val SPADE_JACK = Card(SPADE, JACK)
        val SPADE_QUEEN = Card(SPADE, QUEEN)
        val SPADE_KING = Card(SPADE, KING)
    }
}
