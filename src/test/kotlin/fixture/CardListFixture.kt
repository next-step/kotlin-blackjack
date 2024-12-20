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
            Card(Suit.HEARTS, CardNumber.KING),
        )
    }

    fun blackjackCardList(): MutableList<Card> {
        return mutableListOf(
            Card(Suit.CLUBS, CardNumber.ACE),
            Card(Suit.DIAMONDS, CardNumber.TEN),
            Card(Suit.SPADES, CardNumber.ACE),
            Card(Suit.SPADES, CardNumber.TEN),
        )
    }

    fun mixedCardList(): MutableList<Card> {
        return mutableListOf(
            Card(Suit.CLUBS, CardNumber.KING),
            Card(Suit.CLUBS, CardNumber.QUEEN),
            Card(Suit.CLUBS, CardNumber.JACK),
            Card(Suit.CLUBS, CardNumber.TEN),
            Card(Suit.CLUBS, CardNumber.FIVE),
            Card(Suit.CLUBS, CardNumber.SIX),
            Card(Suit.CLUBS, CardNumber.SEVEN),
            Card(Suit.CLUBS, CardNumber.EIGHT),
            Card(Suit.CLUBS, CardNumber.NINE),
            Card(Suit.CLUBS, CardNumber.TEN),
            Card(Suit.CLUBS, CardNumber.JACK),
            Card(Suit.CLUBS, CardNumber.QUEEN),
            Card(Suit.CLUBS, CardNumber.KING),
            Card(Suit.HEARTS, CardNumber.ACE),
            Card(Suit.HEARTS, CardNumber.TWO),
            Card(Suit.HEARTS, CardNumber.THREE),
            Card(Suit.HEARTS, CardNumber.FOUR),
            Card(Suit.HEARTS, CardNumber.FIVE),
            Card(Suit.HEARTS, CardNumber.SIX),
            Card(Suit.HEARTS, CardNumber.SEVEN),
            Card(Suit.HEARTS, CardNumber.EIGHT),
            Card(Suit.HEARTS, CardNumber.NINE),
            Card(Suit.HEARTS, CardNumber.TEN),
            Card(Suit.HEARTS, CardNumber.JACK),
            Card(Suit.HEARTS, CardNumber.QUEEN),
            Card(Suit.HEARTS, CardNumber.KING),
            Card(Suit.DIAMONDS, CardNumber.ACE),
            Card(Suit.DIAMONDS, CardNumber.TWO),
            Card(Suit.DIAMONDS, CardNumber.THREE),
            Card(Suit.DIAMONDS, CardNumber.FOUR),
            Card(Suit.DIAMONDS, CardNumber.FIVE),
            Card(Suit.DIAMONDS, CardNumber.SIX),
            Card(Suit.DIAMONDS, CardNumber.SEVEN),
            Card(Suit.DIAMONDS, CardNumber.EIGHT),
            Card(Suit.DIAMONDS, CardNumber.NINE),
            Card(Suit.DIAMONDS, CardNumber.TEN),
            Card(Suit.DIAMONDS, CardNumber.JACK),
            Card(Suit.DIAMONDS, CardNumber.QUEEN),
            Card(Suit.DIAMONDS, CardNumber.KING),
            Card(Suit.SPADES, CardNumber.ACE),
            Card(Suit.SPADES, CardNumber.TWO),
            Card(Suit.SPADES, CardNumber.THREE),
            Card(Suit.SPADES, CardNumber.FOUR),
            Card(Suit.SPADES, CardNumber.FIVE),
            Card(Suit.SPADES, CardNumber.SIX),
            Card(Suit.SPADES, CardNumber.SEVEN),
            Card(Suit.SPADES, CardNumber.EIGHT),
            Card(Suit.SPADES, CardNumber.NINE),
            Card(Suit.SPADES, CardNumber.TEN),
            Card(Suit.SPADES, CardNumber.JACK),
            Card(Suit.SPADES, CardNumber.QUEEN),
            Card(Suit.SPADES, CardNumber.KING),
        )
    }
}
