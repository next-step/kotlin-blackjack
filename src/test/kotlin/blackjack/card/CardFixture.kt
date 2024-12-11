package blackjack.card

object CardFixture {
    fun generateTestCard(rank: Rank) = Card(rank = rank, suit = Suit.SPADE)

    fun generateBlackJack(): List<Card> = listOf(
        Card(rank = Rank.ACE, suit = Suit.SPADE),
        Card(rank = Rank.TEN, suit = Suit.SPADE),
    )
}
