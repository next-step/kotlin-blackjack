package blackjack.step2.domain

sealed class Participant(
    open val name: String,
    open val cards: Cards,
) {
    abstract fun pickCard(card: Card): Participant

    abstract fun canDraw(): Boolean

    fun isBust(): Boolean = cards.totalScore() > BLACKJACK_SCORE

    fun score(): Int = cards.totalScore()

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
