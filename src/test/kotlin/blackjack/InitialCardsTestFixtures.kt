package blackjack

object InitialCardsTestFixtures {
    val initial18Cards = listOf(Card(Number(9), Suit.SPADES), Card(Number(9), Suit.DIAMONDS))
    val initial18Cards2 = listOf(Card(Number(9), Suit.HEARTS), Card(Number(9), Suit.CLUBS))

    val initial20Cards = listOf(Card(Number(10), Suit.HEARTS), Card(CardNumber.Jack, Suit.HEARTS))

    val initial19Cards = listOf(Card(Number(10), Suit.CLUBS), Card(Number(9), Suit.CLUBS))

    val initial16Cards = listOf(Card(Number(10), Suit.CLUBS), Card(Number(6), Suit.CLUBS))

    val blackjackCards = listOf(Card(CardNumber.Ace, Suit.SPADES), Card(CardNumber.Jack, Suit.SPADES))
    val blackjackCards2 = listOf(Card(CardNumber.Ace, Suit.CLUBS), Card(CardNumber.Jack, Suit.CLUBS))
}
