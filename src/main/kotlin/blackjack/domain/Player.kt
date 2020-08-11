package blackjack.domain

data class Player(private val name: String) {
    private var _cards: Cards = Cards(emptySet())
    val cards: Cards
        get() = _cards

    fun draw(newCard: Card): Set<Card> {
        return _cards.add(newCard)
    }

    fun hasScoreMoreThanMax() = cards.isMoreThanMaxScore(cards)

    override fun toString(): String {
        return name
    }
}
