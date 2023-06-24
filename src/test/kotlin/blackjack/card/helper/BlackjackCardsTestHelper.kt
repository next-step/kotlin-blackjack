package blackjack.card.helper

import domain.card.card
import domain.card.Cards
import domain.card.CardNumber
import domain.card.Suit
import org.junit.jupiter.params.provider.Arguments

object BlackjackCardsTestHelper {

    fun getCardNumberSumLessThanEquals21(): List<Arguments> = listOf(
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.SPADE, number = CardNumber.ACE),
                    card(suit = Suit.HEART, number = CardNumber.TWO),
                    card(suit = Suit.CLUB, number = CardNumber.THREE),
                ),
            ),
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.SPADE, number = CardNumber.KING),
                    card(suit = Suit.HEART, number = CardNumber.NINE),
                    card(suit = Suit.CLUB, number = CardNumber.ACE),
                ),
            ),
        ),
    )

    fun getCardNumberSum(): List<Arguments> = listOf(
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.SPADE, number = CardNumber.ACE),
                    card(suit = Suit.HEART, number = CardNumber.TWO),
                    card(suit = Suit.CLUB, number = CardNumber.THREE),
                ),
            ),
            16,
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.SPADE, number = CardNumber.KING),
                    card(suit = Suit.HEART, number = CardNumber.NINE),
                    card(suit = Suit.CLUB, number = CardNumber.ACE),
                ),
            ),
            20,
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.SPADE, number = CardNumber.KING),
                    card(suit = Suit.CLUB, number = CardNumber.ACE),
                ),
            ),
            21,
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.ACE),
                    card(suit = Suit.HEART, number = CardNumber.ACE),
                    card(suit = Suit.SPADE, number = CardNumber.ACE),
                    card(suit = Suit.DIAMOND, number = CardNumber.ACE),
                    card(suit = Suit.HEART, number = CardNumber.ACE),
                    card(suit = Suit.SPADE, number = CardNumber.ACE),
                    card(suit = Suit.CLUB, number = CardNumber.ACE),
                    card(suit = Suit.CLUB, number = CardNumber.FIVE),
                ),
            ),
            12,
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.ACE),
                    card(suit = Suit.HEART, number = CardNumber.ACE),
                    card(suit = Suit.SPADE, number = CardNumber.ACE),
                    card(suit = Suit.DIAMOND, number = CardNumber.ACE),
                    card(suit = Suit.HEART, number = CardNumber.ACE),
                    card(suit = Suit.SPADE, number = CardNumber.ACE),
                    card(suit = Suit.CLUB, number = CardNumber.FIVE),
                    card(suit = Suit.CLUB, number = CardNumber.FIVE),
                ),
            ),
            16,
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.ACE),
                    card(suit = Suit.HEART, number = CardNumber.TWO),
                    card(suit = Suit.SPADE, number = CardNumber.THREE),
                    card(suit = Suit.DIAMOND, number = CardNumber.FOUR),
                    card(suit = Suit.HEART, number = CardNumber.FIVE),
                    card(suit = Suit.SPADE, number = CardNumber.ACE),
                    card(suit = Suit.CLUB, number = CardNumber.SIX),
                ),
            ),
            22,
        ),
    )
}
