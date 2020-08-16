package blackjack.model.status

data class Score(
    private val score: Int
) {
    fun isBurst() = score > BLACKJACK

    fun isBlackJack() = score == BLACKJACK

    fun getGameResult(dealer: Score) =
        when {
            isBurst() -> Finish.DEFEAT
            dealer.isBurst() -> Finish.WIN
            isSmallScoreThan(dealer.score) -> Finish.DEFEAT
            isBigScoreThan(dealer.score) -> Finish.WIN
            else -> Finish.DRAW
        }

    private fun isBigScoreThan(number: Int) = number < score

    private fun isSmallScoreThan(number: Int) = number > score

    override fun toString() = score.toString()

    companion object {
        const val BLACKJACK = 21
    }
}
