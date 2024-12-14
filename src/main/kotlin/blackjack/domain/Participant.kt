package blackjack.domain

abstract class Participant(
    private val drawCard: () -> Card,
    initialCardCount: Int = 2
) {
    private val _cards = mutableListOf<Card>()
    val cards: Cards = Cards(_cards)
    val cardsSum: Int get() = cards.sumValues()

    init {
        repeat(initialCardCount) { addCard(drawCard()) }
    }

    private fun addCard(card: Card) {
        _cards.add(card)
    }

    fun addCardIfAvailable(
        requireCard: () -> Card,
        onDrawCard: () -> Unit
    ): Boolean {
        if (isAddCardEnabled()) {
            addCard(requireCard())
            onDrawCard()
            return true
        }
        return false
    }

    abstract fun isAddCardEnabled(): Boolean
}