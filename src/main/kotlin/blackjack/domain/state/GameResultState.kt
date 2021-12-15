package blackjack.domain.state

enum class GameResultState(val displayName: String) {
    Win("승"), Draw("무"), Lose("패");

    companion object {

        private const val ZERO = 0

        fun compareStayScore(compareValue: Int): GameResultState {
            return when {
                compareValue > ZERO -> Win
                compareValue == ZERO -> Draw
                else -> Lose
            }
        }
    }
}
