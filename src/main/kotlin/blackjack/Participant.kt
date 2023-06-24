package blackjack

sealed class Participant(
    val name: String,
    var cards: Cards,
) {
    abstract fun openedCards(): Cards

    fun hit(card: Card) {
        val addedCards = cards + card
        if (addedCards.calculateScore() <= Cards.WINNING_NUMBER) {
            cards = addedCards
        }
    }

    fun calculateScore(): Int = cards.calculateScore()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Participant) return false

        if (name != other.name) return false
        return cards == other.cards
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + cards.hashCode()
        return result
    }
}
