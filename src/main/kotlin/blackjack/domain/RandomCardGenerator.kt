package blackjack.domain

class RandomCardGenerator: CardGenerator {
    override fun generate(): Card {
        return Card(Rank.random(), Suit.random())
    }
}