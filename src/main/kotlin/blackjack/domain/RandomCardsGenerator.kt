package blackjack.domain

object RandomCardsGenerator: CardsGenerator {
    override fun generate(): List<Card> {
        return Suit.values().flatMap { suit ->
            Denomination.values().map { denomination ->
                Card(suit, denomination)
            }
        }.shuffled()
    }
}
