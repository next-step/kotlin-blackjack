package blackjack.domain.card

object CardFactory {
    val defaultCards = Suit.values()
        .flatMap { suit ->
            createCards(suit)
        }.shuffled()

    private fun createCards(suit: Suit) =
        Denomination.values().map { denomination -> Card(denomination, suit) }
}
