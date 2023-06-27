package blackjack.domain

sealed class Participant(
    val name: String,
    var cards: Cards,
    var state: ParticipantState = Alive
) {
    abstract fun openedCards(): Cards

    fun hit(card: Card) {
        if (isBurst()) {
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
}
