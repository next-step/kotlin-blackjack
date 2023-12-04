package blackjack.domain

object RandomCardsGenerator: CardsGenerator {
    override fun generate(): Cards {
        return Suit.values().flatMap { suit ->
            Denomination.values().map { denomination ->
                Card(suit, denomination)
            }
        }.shuffled().toCards()
    }
}
