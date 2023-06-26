package blackjack.domain

sealed class Participant(
    val name: String,
    var cards: Cards,
    var state: ParticipantState = Alive
) {
    abstract fun openedCards(): Cards

    fun hit(card: Card) {
        if (state.isBurst()) {
            return
        }

        val addedCards = cards + card
        if (addedCards.calculateScore().isBurst()) {
            state = Burst
        }
        cards = addedCards
    }

    fun calculateScore(): Score = cards.calculateScore()

    fun isBurst(): Boolean = state.isBurst()

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
