package blackjack.business

object CardFactory {
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

    fun getCards(): List<Card> = allCards
}
