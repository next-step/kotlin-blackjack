package blackjack.card.helper

import domain.card.Card
import domain.card.CardNumber
import domain.card.Suit
import org.junit.jupiter.params.provider.Arguments

object BlackjackCardsTestHelper {

    fun getCardNumberSumLessThanEquals21(): List<Arguments> = listOf(
        Arguments.of(
            CardsTestFactory.makeCards(
                Card(suit = Suit.SPADE, number = CardNumber.ACE),
                Card(suit = Suit.HEART, number = CardNumber.TWO),
                Card(suit = Suit.CLUB, number = CardNumber.THREE),
            ),
        ),
        Arguments.of(
            CardsTestFactory.makeCards(
                Card(suit = Suit.SPADE, number = CardNumber.KING),
                Card(suit = Suit.HEART, number = CardNumber.NINE),
                Card(suit = Suit.CLUB, number = CardNumber.ACE),
            ),
        ),
    )

    fun getCardNumberSum(): List<Arguments> = listOf(
        Arguments.of(
            CardsTestFactory.makeCards(
                Card(suit = Suit.SPADE, number = CardNumber.ACE),
                Card(suit = Suit.HEART, number = CardNumber.TWO),
                Card(suit = Suit.CLUB, number = CardNumber.THREE),
            ),
            16,
        ),
        Arguments.of(
            CardsTestFactory.makeCards(
                Card(suit = Suit.SPADE, number = CardNumber.KING),
                Card(suit = Suit.HEART, number = CardNumber.NINE),
                Card(suit = Suit.CLUB, number = CardNumber.ACE),
            ),
            20,
        ),
        Arguments.of(
            CardsTestFactory.makeCards(
                Card(suit = Suit.SPADE, number = CardNumber.KING),
                Card(suit = Suit.CLUB, number = CardNumber.ACE),
            ),
            21,
        ),
        Arguments.of(
            CardsTestFactory.makeCards(
                Card(suit = Suit.CLUB, number = CardNumber.ACE),
                Card(suit = Suit.HEART, number = CardNumber.ACE),
                Card(suit = Suit.SPADE, number = CardNumber.ACE),
                Card(suit = Suit.DIAMOND, number = CardNumber.ACE),
                Card(suit = Suit.HEART, number = CardNumber.ACE),
                Card(suit = Suit.SPADE, number = CardNumber.ACE),
                Card(suit = Suit.CLUB, number = CardNumber.ACE),
                Card(suit = Suit.CLUB, number = CardNumber.FIVE),
            ),
            12,
        ),
        Arguments.of(
            CardsTestFactory.makeCards(
                Card(suit = Suit.CLUB, number = CardNumber.ACE),
                Card(suit = Suit.HEART, number = CardNumber.ACE),
                Card(suit = Suit.SPADE, number = CardNumber.ACE),
                Card(suit = Suit.DIAMOND, number = CardNumber.ACE),
                Card(suit = Suit.HEART, number = CardNumber.ACE),
                Card(suit = Suit.SPADE, number = CardNumber.ACE),
                Card(suit = Suit.CLUB, number = CardNumber.FIVE),
                Card(suit = Suit.CLUB, number = CardNumber.FIVE),
            ),
            16,
        ),
        Arguments.of(
            CardsTestFactory.makeCards(
                Card(suit = Suit.CLUB, number = CardNumber.ACE),
                Card(suit = Suit.HEART, number = CardNumber.TWO),
                Card(suit = Suit.SPADE, number = CardNumber.THREE),
                Card(suit = Suit.DIAMOND, number = CardNumber.FOUR),
                Card(suit = Suit.HEART, number = CardNumber.FIVE),
                Card(suit = Suit.SPADE, number = CardNumber.ACE),
                Card(suit = Suit.CLUB, number = CardNumber.SIX),
            ),
            22,
        ),
    )
}
