package blackjack.card.helper

import domain.card.Card
import domain.card.CardNumber
import domain.card.Suit
import org.junit.jupiter.params.provider.Arguments

object StartStateTestHelper {
    fun getStartStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            Card(suit = Suit.HEART, number = CardNumber.ACE),
            Card(suit = Suit.CLUB, number = CardNumber.TWO),
        ),
        Arguments.of(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.HEART, number = CardNumber.JACK),
        ),
        Arguments.of(
            Card(suit = Suit.SPADE, number = CardNumber.TEN),
            Card(suit = Suit.HEART, number = CardNumber.FIVE),
        ),
    )

    fun getBlackjackStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            Card(suit = Suit.HEART, number = CardNumber.ACE),
            Card(suit = Suit.CLUB, number = CardNumber.JACK),
        ),
        Arguments.of(
            Card(suit = Suit.SPADE, number = CardNumber.ACE),
            Card(suit = Suit.HEART, number = CardNumber.QUEEN),
        ),
        Arguments.of(
            Card(suit = Suit.SPADE, number = CardNumber.TEN),
            Card(suit = Suit.HEART, number = CardNumber.ACE),
        ),
        Arguments.of(
            Card(suit = Suit.SPADE, number = CardNumber.KING),
            Card(suit = Suit.HEART, number = CardNumber.ACE),
        ),
    )

    fun getHitStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            Card(suit = Suit.HEART, number = CardNumber.ACE),
            Card(suit = Suit.CLUB, number = CardNumber.TWO),
            Card(suit = Suit.CLUB, number = CardNumber.TWO),
        ),
        Arguments.of(
            Card(suit = Suit.SPADE, number = CardNumber.TWO),
            Card(suit = Suit.HEART, number = CardNumber.FIVE),
            Card(suit = Suit.HEART, number = CardNumber.TEN),
        ),
        Arguments.of(
            Card(suit = Suit.SPADE, number = CardNumber.TEN),
            Card(suit = Suit.HEART, number = CardNumber.FIVE),
            Card(suit = Suit.HEART, number = CardNumber.FIVE),
        ),
    )
}
