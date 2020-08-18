package blackjack.domain

interface Participant {
    val cards: Cards

    fun draw(newCard: Card?): Cards?

    fun stateOfPlayer(needDrawing: String, score: Int, cardCount: Int): PlayerState =
        PlayerState.valueOfState(needDrawing, totalScore(), countOfCards())

    fun isHit(state: PlayerState) = state == PlayerState.HIT

    fun isBust(state: PlayerState): Boolean = state == PlayerState.BUST

    fun countOfCards(): Int = cards.size()

    fun totalScore(): Int = cards.sumOfScores()

    fun stateOfCards(): String = cards.toString()
}
