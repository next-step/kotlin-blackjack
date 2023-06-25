package blackjack.domain

sealed class Participant(
    val name: String,
    var cards: Cards,
    var burst: Boolean = false
) {
    abstract fun openedCards(): Cards

    fun hit(card: Card) {
        if (cards.calculateScore().isBurst()) {
            return
        }

        val addedCards = cards + card
        if (addedCards.calculateScore().isBurst()) {
            burst = true
        }
        cards = addedCards
    }

    fun calculateScore(): Score = cards.calculateScore()

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
