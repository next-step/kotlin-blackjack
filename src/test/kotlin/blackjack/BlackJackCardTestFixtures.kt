package blackjack

import blackjack.Denomination.ACE
import blackjack.Denomination.EIGHT
import blackjack.Denomination.FIVE
import blackjack.Denomination.FOUR
import blackjack.Denomination.KING
import blackjack.Denomination.NINE
import blackjack.Denomination.SEVEN
import blackjack.Denomination.TEN
import blackjack.Denomination.TWO
import blackjack.Suit.CLUBS
import blackjack.Suit.DIAMONDS
import blackjack.Suit.HEARTS
import blackjack.Suit.SPADES
import io.kotest.data.row

object BlackJackCardTestFixtures {
    val ace0Rows =
        listOf(
            row(
                listOf(
                    Card(SPADES, TEN),
                    Card(SPADES, KING),
                ),
                20,
            ),
            row(
                listOf(
                    Card(SPADES, TEN),
                    Card(SPADES, SEVEN),
                ),
                17,
            ),
            row(
                listOf(
                    Card(SPADES, TEN),
                    Card(SPADES, SEVEN),
                    Card(SPADES, FIVE),
                ),
                22,
            ),
            row(
                listOf(
                    Card(SPADES, TEN),
                    Card(SPADES, SEVEN),
                    Card(SPADES, FOUR),
                ),
                21,
            ),
            row(
                listOf(
                    Card(SPADES, SEVEN),
                    Card(SPADES, FOUR),
                ),
                11,
            ),
        )

    val ace1Rows =
        listOf(
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(SPADES, KING),
                ),
                21,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(SPADES, NINE),
                ),
                20,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(SPADES, NINE),
                    Card(SPADES, KING),
                ),
                20,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(SPADES, TEN),
                    Card(SPADES, KING),
                ),
                21,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(SPADES, TWO),
                ),
                13,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(SPADES, TWO),
                    Card(SPADES, NINE),
                ),
                12,
            ),
        )

    val ace2Rows =
        listOf(
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                ),
                12,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(HEARTS, NINE),
                ),
                21,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(HEARTS, TEN),
                ),
                12,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(HEARTS, TEN),
                    Card(HEARTS, KING),
                ),
                22,
            ),
        )

    val ace3Rows =
        listOf(
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(CLUBS, ACE),
                ),
                13,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(CLUBS, ACE),
                    Card(CLUBS, EIGHT),
                ),
                21,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(CLUBS, ACE),
                    Card(CLUBS, NINE),
                ),
                12,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(CLUBS, ACE),
                    Card(CLUBS, NINE),
                    Card(DIAMONDS, NINE),
                ),
                21,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(CLUBS, ACE),
                    Card(CLUBS, NINE),
                    Card(CLUBS, TEN),
                ),
                22,
            ),
        )

    val ace4Rows =
        listOf(
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(CLUBS, ACE),
                    Card(DIAMONDS, ACE),
                ),
                14,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(CLUBS, ACE),
                    Card(DIAMONDS, ACE),
                    Card(DIAMONDS, SEVEN),
                ),
                21,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(CLUBS, ACE),
                    Card(DIAMONDS, ACE),
                    Card(DIAMONDS, EIGHT),
                ),
                12,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(CLUBS, ACE),
                    Card(DIAMONDS, ACE),
                    Card(DIAMONDS, EIGHT),
                    Card(DIAMONDS, NINE),
                ),
                21,
            ),
            row(
                listOf(
                    Card(SPADES, ACE),
                    Card(HEARTS, ACE),
                    Card(CLUBS, ACE),
                    Card(DIAMONDS, ACE),
                    Card(DIAMONDS, EIGHT),
                    Card(DIAMONDS, TEN),
                ),
                22,
            ),
        )
}
