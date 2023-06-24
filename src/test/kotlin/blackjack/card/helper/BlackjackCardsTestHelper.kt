package blackjack.card.helper

import domain.card.BlackjackCard
import domain.card.BlackjackCards
import domain.card.CardNumber
import domain.card.Suit
import org.junit.jupiter.params.provider.Arguments

object BlackjackCardsTestHelper {

    fun getCardNumberSumLessThanEquals21(): List<Arguments> = listOf(
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.TWO),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.THREE),
                ),
            ),
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.KING),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.NINE),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                ),
            ),
        ),
    )

    fun getCardNumberSum(): List<Arguments> = listOf(
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.TWO),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.THREE),
                ),
            ),
            16,
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.KING),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.NINE),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                ),
            ),
            20,
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.KING),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                ),
            ),
            21,
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.DIAMOND, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.FIVE),
                ),
            ),
            12,
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.DIAMOND, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.FIVE),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.FIVE),
                ),
            ),
            16,
        ),
        Arguments.of(
            BlackjackCards(
                listOf(
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.TWO),
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.THREE),
                    BlackjackCard(suit = Suit.DIAMOND, number = CardNumber.FOUR),
                    BlackjackCard(suit = Suit.HEART, number = CardNumber.FIVE),
                    BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                    BlackjackCard(suit = Suit.CLUB, number = CardNumber.SIX),
                ),
            ),
            22,
        ),
    )
}
