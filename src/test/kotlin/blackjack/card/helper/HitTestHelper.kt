package blackjack.card.helper

import domain.card.Card
import domain.card.Cards
import domain.card.CardNumber
import domain.card.Suit
import org.junit.jupiter.params.provider.Arguments

object HitTestHelper {

    fun getHitStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            Cards(listOf(Card(suit = Suit.CLUB, number = CardNumber.KING))),
            Card(suit = Suit.SPADE, number = CardNumber.FIVE),
        ),
        Arguments.of(
            Cards(
                listOf(
                    Card(suit = Suit.CLUB, number = CardNumber.ACE),
                    Card(suit = Suit.CLUB, number = CardNumber.TWO),
                ),
            ),
            Card(suit = Suit.SPADE, number = CardNumber.FIVE),
        ),
        Arguments.of(
            Cards(
                listOf(
                    Card(suit = Suit.CLUB, number = CardNumber.TEN),
                    Card(suit = Suit.HEART, number = CardNumber.TEN),
                ),
            ),
            Card(suit = Suit.SPADE, number = CardNumber.ACE),
        ),
    )

    fun getBurstStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            Cards(
                listOf(
                    Card(suit = Suit.CLUB, number = CardNumber.KING),
                    Card(suit = Suit.HEART, number = CardNumber.KING),
                ),
            ),
            Card(suit = Suit.SPADE, number = CardNumber.FIVE),
        ),
        Arguments.of(
            Cards(
                listOf(
                    Card(suit = Suit.CLUB, number = CardNumber.ACE),
                    Card(suit = Suit.CLUB, number = CardNumber.TWO),
                    Card(suit = Suit.SPADE, number = CardNumber.SEVEN),
                    Card(suit = Suit.SPADE, number = CardNumber.JACK),
                ),
            ),
            Card(suit = Suit.SPADE, number = CardNumber.TWO),
        ),
        Arguments.of(
            Cards(
                listOf(
                    Card(suit = Suit.CLUB, number = CardNumber.TEN),
                    Card(suit = Suit.HEART, number = CardNumber.TEN),
                ),
            ),
            Card(suit = Suit.SPADE, number = CardNumber.TWO),
        ),
    )

    fun getStandStateTestData(): List<Arguments> = listOf(
        Arguments.of(
            Cards(
                listOf(
                    Card(suit = Suit.CLUB, number = CardNumber.KING),
                    Card(suit = Suit.HEART, number = CardNumber.KING),
                ),
            ),
        ),
        Arguments.of(
            Cards(
                listOf(
                    Card(suit = Suit.CLUB, number = CardNumber.ACE),
                    Card(suit = Suit.CLUB, number = CardNumber.TWO),
                    Card(suit = Suit.SPADE, number = CardNumber.SEVEN),
                    Card(suit = Suit.SPADE, number = CardNumber.JACK),
                ),
            ),
        ),
        Arguments.of(
            Cards(
                listOf(
                    Card(suit = Suit.CLUB, number = CardNumber.TEN),
                    Card(suit = Suit.HEART, number = CardNumber.TEN),
                ),
            ),
        ),
    )
}
