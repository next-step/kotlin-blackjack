package blackjack.domain

sealed class Participant(
    val name: String,
    var state: State
) {
    abstract fun openedCards(): Cards

    fun isFinished(): Boolean = state is Finished

    fun hit(card: Card) {
        if (isFinished()) {
            return
        }
        state = state.hit(card)
    }

    fun stay() {
        if (isFinished()) {
            return
        }
        state = state.stay()
    }

    fun calculateScore(): Score = state.calculateScore()
}
