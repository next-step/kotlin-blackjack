package domain

sealed class GameParticipator {

    val cards: Cards = Cards()

    fun takeCards(vararg cards: Card) {
        cards.forEach { this.cards.add(it) }
    }

    fun choiceBestScore(): Int = cards.bestScore()
    fun isLoser() = choiceBestScore() > Score.MAX_SCORE

    abstract fun canDrawCard(): Boolean
}
