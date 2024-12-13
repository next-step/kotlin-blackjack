package blackjack.step2.domain

sealed class Participant(
    open val name: String,
    open val cards: Cards,
) {
    abstract fun pickCard(card: Card): Participant

    abstract fun canDraw(): Boolean

    abstract fun playTurn(
        cardPicker: CardPicker,
        interactor: GameInteractor,
    ): Participant

    fun isBust(): Boolean = cards.totalScore() > BLACKJACK_SCORE

    fun score(): Int = cards.totalScore()

    fun isInitialBlackjack(): Boolean = cards.isInitialBlackjack()

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
