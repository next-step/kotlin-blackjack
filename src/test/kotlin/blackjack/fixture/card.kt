package blackjack.fixture

import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.CardNumber
import blackjack.domain.CardSuit

val HEART_KING = Card(CardSuit.HEART, CardNumber.KING)
val HEART_EIGHT = Card(CardSuit.HEART, CardNumber.EIGHT)
val HEART_SEVEN = Card(CardSuit.HEART, CardNumber.SEVEN)
val HEART_SIX = Card(CardSuit.HEART, CardNumber.SIX)
val HEART_TWO = Card(CardSuit.HEART, CardNumber.TWO)

val DIAMOND_KING = Card(CardSuit.DIAMOND, CardNumber.KING)

val SPADE_ACE = Card(CardSuit.SPADE, CardNumber.ACE)

class CustomCardDeck(val cards: MutableList<Card>) : CardDeck {
    override fun fetch(): Card = cards.removeFirst()

    override fun countOfCards(): Int = cards.size

    companion object {
        fun of(vararg cards: Card): CustomCardDeck = CustomCardDeck(cards.toMutableList())
    }
}
