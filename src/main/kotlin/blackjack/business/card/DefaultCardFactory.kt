package blackjack.business.card

class DefaultCardFactory : CardFactory {
    private val allCards: List<Card> = Suit.values().flatMap { suit ->
        Rank.values().map { rank ->
            Card(suit, rank)
        }
    }

    override fun getCards(): List<Card> = allCards
}
