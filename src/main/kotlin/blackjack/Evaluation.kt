package blackjack

enum class Evaluation {
    WIN, LOSE, CONTINUE;

    companion object {
        private const val WINNING_CARD_NUMBER_SUM = 21

        fun from(sumOfNumbers: Int): Evaluation {
            return when (sumOfNumbers) {
                WINNING_CARD_NUMBER_SUM -> WIN
                in 0 until WINNING_CARD_NUMBER_SUM -> CONTINUE
                else -> LOSE
            }
        }
    }
}
