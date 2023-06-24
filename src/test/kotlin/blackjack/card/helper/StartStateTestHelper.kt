package blackjack.card.helper

import domain.card.BlackjackCard
import domain.card.CardNumber
import domain.card.Suit
import org.junit.jupiter.params.provider.Arguments

object StartStateTestHelper {
    fun getStartStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
            BlackjackCard(suit = Suit.CLUB, number = CardNumber.TWO),
        ),
        Arguments.of(
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.JACK),
            BlackjackCard(suit = Suit.HEART, number = CardNumber.JACK),
        ),
        Arguments.of(
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.TEN),
            BlackjackCard(suit = Suit.HEART, number = CardNumber.FIVE),
        ),
    )

    fun getBlackjackStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
            BlackjackCard(suit = Suit.CLUB, number = CardNumber.JACK),
        ),
        Arguments.of(
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
            BlackjackCard(suit = Suit.HEART, number = CardNumber.QUEEN),
        ),
        Arguments.of(
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.TEN),
            BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
        ),
        Arguments.of(
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.KING),
            BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
        ),
    )

    fun getHitStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
            BlackjackCard(suit = Suit.CLUB, number = CardNumber.TWO),
            BlackjackCard(suit = Suit.CLUB, number = CardNumber.TWO),
        ),
        Arguments.of(
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.TWO),
            BlackjackCard(suit = Suit.HEART, number = CardNumber.FIVE),
            BlackjackCard(suit = Suit.HEART, number = CardNumber.TEN),
        ),
        Arguments.of(
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.TEN),
            BlackjackCard(suit = Suit.HEART, number = CardNumber.FIVE),
            BlackjackCard(suit = Suit.HEART, number = CardNumber.FIVE),
        ),
    )
}
