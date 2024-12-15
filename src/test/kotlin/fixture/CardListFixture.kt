package fixture

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Suit

object CardListFixture {
    fun simpleCardList(): MutableList<Card> {
        return mutableListOf(
            Card(Suit.CLUBS, CardNumber.ACE),
            Card(Suit.DIAMONDS, CardNumber.TWO),
            Card(Suit.HEARTS, CardNumber.THREE),
            Card(Suit.SPADES, CardNumber.TEN),
            Card(Suit.HEARTS, CardNumber.SIX),
            Card(Suit.SPADES, CardNumber.NINE),
            Card(Suit.HEARTS, CardNumber.NINE),
        )
    }
}
