package blackjack.domain

sealed class Player(
    val name: String,
    val bet: Money = Money(),
    deck: Deck = Deck()
) {
    internal val deck: Deck = deck.copy()

    open fun addCard(card: Card) {
        deck.add(card)
    }

    fun addCardAll(values: Collection<Card>) = values.forEach(::addCard)

    fun currentDeck(): Deck = deck.copy()

    fun calculateScore(): Int = deck.score()

    abstract fun isAddable(): Boolean
}
