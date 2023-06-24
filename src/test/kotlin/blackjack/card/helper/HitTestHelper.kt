package blackjack.card.helper

import domain.card.BlackjackCard
import domain.card.BlackjackCards
import domain.card.CardNumber
import domain.card.Suit
import org.junit.jupiter.params.provider.Arguments

object HitTestHelper {

    fun getHitStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            BlackjackCards(listOf(BlackjackCard(suit = Suit.CLUB, number = CardNumber.KING))),
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.FIVE),
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.TWO),
                ),
            ),
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.FIVE),
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.TEN),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.TEN),
                ),
            ),
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
        ),
    )

    fun getBurstStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.KING),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.KING),
                ),
            ),
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.FIVE),
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.TWO),
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.SEVEN),
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.JACK),
                ),
            ),
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.TWO),
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.TEN),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.TEN),
                ),
            ),
            BlackjackCard(suit = Suit.SPADE, number = CardNumber.TWO),
        ),
    )

    fun getStandStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.KING),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.KING),
                ),
            ),
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.TWO),
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.SEVEN),
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.JACK),
                ),
            ),
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.TEN),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.TEN),
                ),
            ),
        ),
    )
}
