package blackjack.model.status

data class Score(
    private val score: Int
) {
    fun isBurst() = score > BLACKJACK

    fun isBlackJack() = score == BLACKJACK

    fun getGameResult(dealer: Score) =
        when {
            isBurst() -> GameResult.DEFEAT
            dealer.isBurst() -> GameResult.WIN
            isSmallScoreThan(dealer.score) -> GameResult.DEFEAT
            isBigScoreThan(dealer.score) -> GameResult.WIN
            else -> GameResult.DRAW
        }

    private fun isBigScoreThan(number: Int) = number < score

    private fun isSmallScoreThan(number: Int) = number > score

    override fun toString() = score.toString()

    companion object {
        const val BLACKJACK = 21
    }
}
