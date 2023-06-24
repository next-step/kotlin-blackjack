package blackjack.card.helper

import domain.card.card
import domain.card.Cards
import domain.card.CardNumber
import domain.card.Suit
import org.junit.jupiter.params.provider.Arguments

object HitTestHelper {

    fun getHitStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            Cards(listOf(card(suit = Suit.CLUB, number = CardNumber.KING))),
            card(suit = Suit.SPADE, number = CardNumber.FIVE),
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.ACE),
                    card(suit = Suit.CLUB, number = CardNumber.TWO),
                ),
            ),
            card(suit = Suit.SPADE, number = CardNumber.FIVE),
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.TEN),
                    card(suit = Suit.HEART, number = CardNumber.TEN),
                ),
            ),
            card(suit = Suit.SPADE, number = CardNumber.ACE),
        ),
    )

    fun getBurstStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.KING),
                    card(suit = Suit.HEART, number = CardNumber.KING),
                ),
            ),
            card(suit = Suit.SPADE, number = CardNumber.FIVE),
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.ACE),
                    card(suit = Suit.CLUB, number = CardNumber.TWO),
                    card(suit = Suit.SPADE, number = CardNumber.SEVEN),
                    card(suit = Suit.SPADE, number = CardNumber.JACK),
                ),
            ),
            card(suit = Suit.SPADE, number = CardNumber.TWO),
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.TEN),
                    card(suit = Suit.HEART, number = CardNumber.TEN),
                ),
            ),
            card(suit = Suit.SPADE, number = CardNumber.TWO),
        ),
    )

    fun getStandStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.KING),
                    card(suit = Suit.HEART, number = CardNumber.KING),
                ),
            ),
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.ACE),
                    card(suit = Suit.CLUB, number = CardNumber.TWO),
                    card(suit = Suit.SPADE, number = CardNumber.SEVEN),
                    card(suit = Suit.SPADE, number = CardNumber.JACK),
                ),
            ),
        ),
        Arguments.of(
            Cards(
                listOf(
                    card(suit = Suit.CLUB, number = CardNumber.TEN),
                    card(suit = Suit.HEART, number = CardNumber.TEN),
                ),
            ),
        ),
    )
}
