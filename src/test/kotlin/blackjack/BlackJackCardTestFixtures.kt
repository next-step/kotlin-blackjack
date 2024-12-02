package blackjack

import io.kotest.data.row

object BlackJackCardTestFixtures {
    val ace0Rows =
        listOf(
            row(
                listOf(
                    Card(Number(10), Suit.SPADES),
                    Card(CardNumber.King, Suit.SPADES),
                ),
                20,
            ),
            row(
                listOf(
                    Card(Number(10), Suit.SPADES),
                    Card(Number(7), Suit.SPADES),
                ),
                17,
            ),
            row(
                listOf(
                    Card(Number(10), Suit.SPADES),
                    Card(Number(7), Suit.SPADES),
                    Card(Number(5), Suit.SPADES),
                ),
                22,
            ),
            row(
                listOf(
                    Card(Number(10), Suit.SPADES),
                    Card(Number(7), Suit.SPADES),
                    Card(Number(4), Suit.SPADES),
                ),
                21,
            ),
        )

    val ace1Rows =
        listOf(
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.King, Suit.SPADES),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(Number(9), Suit.SPADES),
                ),
                20,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(Number(9), Suit.SPADES),
                    Card(CardNumber.King, Suit.SPADES),
                ),
                20,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(Number(10), Suit.SPADES),
                    Card(CardNumber.King, Suit.SPADES),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(Number(2), Suit.SPADES),
                ),
                13,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(Number(2), Suit.SPADES),
                    Card(Number(9), Suit.SPADES),
                ),
                12,
            ),
        )

    val ace2Rows =
        listOf(
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                ),
                12,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(Number(9), Suit.HEARTS),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(Number(10), Suit.HEARTS),
                ),
                12,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(Number(10), Suit.HEARTS),
                    Card(CardNumber.King, Suit.HEARTS),
                ),
                22,
            ),
        )

    val ace3Rows =
        listOf(
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(CardNumber.Ace, Suit.CLUBS),
                ),
                13,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(CardNumber.Ace, Suit.CLUBS),
                    Card(Number(8), Suit.CLUBS),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(CardNumber.Ace, Suit.CLUBS),
                    Card(Number(9), Suit.CLUBS),
                ),
                12,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(CardNumber.Ace, Suit.CLUBS),
                    Card(Number(9), Suit.CLUBS),
                    Card(Number(9), Suit.CLUBS),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(CardNumber.Ace, Suit.CLUBS),
                    Card(Number(9), Suit.CLUBS),
                    Card(Number(10), Suit.CLUBS),
                ),
                22,
            ),
        )

    val ace4Rows =
        listOf(
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(CardNumber.Ace, Suit.CLUBS),
                    Card(CardNumber.Ace, Suit.DIAMONDS),
                ),
                14,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(CardNumber.Ace, Suit.CLUBS),
                    Card(CardNumber.Ace, Suit.DIAMONDS),
                    Card(Number(7), Suit.CLUBS),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(CardNumber.Ace, Suit.CLUBS),
                    Card(CardNumber.Ace, Suit.DIAMONDS),
                    Card(Number(8), Suit.CLUBS),
                ),
                12,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(CardNumber.Ace, Suit.CLUBS),
                    Card(CardNumber.Ace, Suit.DIAMONDS),
                    Card(Number(8), Suit.CLUBS),
                    Card(Number(9), Suit.CLUBS),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.Ace, Suit.SPADES),
                    Card(CardNumber.Ace, Suit.HEARTS),
                    Card(CardNumber.Ace, Suit.CLUBS),
                    Card(CardNumber.Ace, Suit.DIAMONDS),
                    Card(Number(8), Suit.CLUBS),
                    Card(Number(10), Suit.CLUBS),
                ),
                22,
            ),
        )
}
