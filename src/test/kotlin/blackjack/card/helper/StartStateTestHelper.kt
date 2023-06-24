package blackjack.card.helper

import domain.card.card
import domain.card.CardNumber
import domain.card.Suit
import org.junit.jupiter.params.provider.Arguments

object StartStateTestHelper {
    fun getStartStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            card(suit = Suit.HEART, number = CardNumber.ACE),
            card(suit = Suit.CLUB, number = CardNumber.TWO),
        ),
        Arguments.of(
            card(suit = Suit.SPADE, number = CardNumber.JACK),
            card(suit = Suit.HEART, number = CardNumber.JACK),
        ),
        Arguments.of(
            card(suit = Suit.SPADE, number = CardNumber.TEN),
            card(suit = Suit.HEART, number = CardNumber.FIVE),
        ),
    )

    fun getBlackjackStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            card(suit = Suit.HEART, number = CardNumber.ACE),
            card(suit = Suit.CLUB, number = CardNumber.JACK),
        ),
        Arguments.of(
            card(suit = Suit.SPADE, number = CardNumber.ACE),
            card(suit = Suit.HEART, number = CardNumber.QUEEN),
        ),
        Arguments.of(
            card(suit = Suit.SPADE, number = CardNumber.TEN),
            card(suit = Suit.HEART, number = CardNumber.ACE),
        ),
        Arguments.of(
            card(suit = Suit.SPADE, number = CardNumber.KING),
            card(suit = Suit.HEART, number = CardNumber.ACE),
        ),
    )

    fun getHitStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            card(suit = Suit.HEART, number = CardNumber.ACE),
            card(suit = Suit.CLUB, number = CardNumber.TWO),
            card(suit = Suit.CLUB, number = CardNumber.TWO),
        ),
        Arguments.of(
            card(suit = Suit.SPADE, number = CardNumber.TWO),
            card(suit = Suit.HEART, number = CardNumber.FIVE),
            card(suit = Suit.HEART, number = CardNumber.TEN),
        ),
        Arguments.of(
            card(suit = Suit.SPADE, number = CardNumber.TEN),
            card(suit = Suit.HEART, number = CardNumber.FIVE),
            card(suit = Suit.HEART, number = CardNumber.FIVE),
        ),
    )
}
