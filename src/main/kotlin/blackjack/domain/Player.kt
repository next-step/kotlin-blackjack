package blackjack.domain

sealed class Player(
    val name: String,
    val bet: Money = Money(),
    deck: Deck = Deck()
) {
    internal val deck: Deck = deck.copy()
    var revenue: Money = Money()

    open fun addCard(card: Card) {
        deck.add(card)
    }

    fun addCardAll(values: Collection<Card>) = values.forEach(::addCard)

    fun currentDeck(): Deck = deck.copy()

    fun calculateScore(): Int = deck.score()

    fun computeEarnings(block: (playerRevenue: Money) -> Money) {
        revenue = block(revenue)
    }

    abstract fun isAddable(): Boolean
}
