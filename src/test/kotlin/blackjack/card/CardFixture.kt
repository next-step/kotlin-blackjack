package blackjack.card

object CardFixture {
    fun generateTestCard(rank: Rank) = Card(rank = rank, suit = Suit.SPADE)
}
