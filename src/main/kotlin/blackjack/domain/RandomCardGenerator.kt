package blackjack.domain

class RandomCardGenerator(
    private val rank: Rank = Rank.random(),
    private val suit: Suit = Suit.random()
) : CardGenerator {
    override fun generate(): Card {
        return Card(rank, suit)
    }
}