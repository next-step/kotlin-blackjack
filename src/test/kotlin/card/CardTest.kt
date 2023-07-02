package card

import card.Denomination.ACE
import card.Denomination.JACK
import card.Denomination.KING
import card.Denomination.QUEEN
import card.Denomination.THREE
import card.Denomination.TWO
import card.Suit.CLOVER
import card.Suit.DIAMOND
import card.Suit.HEART
import card.Suit.SPADE
import io.kotest.core.spec.style.FunSpec

class CardTest : FunSpec({}) {
    companion object {
        val SPADE_ACE = Card(SPADE, ACE)
        val SPADE_TWO = Card(SPADE, TWO)
        val SPADE_THREE = Card(SPADE, THREE)
        val SPADE_JACK = Card(SPADE, JACK)
        val SPADE_QUEEN = Card(SPADE, QUEEN)
        val SPADE_KING = Card(SPADE, KING)
        val CLOVER_ACE = Card(CLOVER, ACE)
        val DIAMOND_ACE = Card(DIAMOND, ACE)
        val HEART_ACE = Card(HEART, ACE)
        val HEART_JACK = Card(HEART, JACK)
        val HEART_QUEEN = Card(HEART, QUEEN)
    }
}
