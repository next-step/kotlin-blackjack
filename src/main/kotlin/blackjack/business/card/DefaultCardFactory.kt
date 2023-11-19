package blackjack.business.card

class DefaultCardFactory : CardFactory {
    private val allCards: List<Card>

    init {
        val cards = mutableListOf<Card>()
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
        allCards = cards
    }

    override fun getCards(): List<Card> = allCards
}
