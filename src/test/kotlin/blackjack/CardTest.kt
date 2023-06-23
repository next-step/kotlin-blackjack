package blackjack

import blackjack.Denomination.*
import blackjack.Suit.SPADE
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
