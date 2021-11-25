package blackjack.domain.player.state

enum class MatchResult {

    WIN, DRAW, LOSE;

    companion object {
        private const val ZERO = 0;

        fun from(compareToValue: Int): MatchResult {
            if (compareToValue > ZERO) {
                return WIN;
            }
            if (compareToValue == ZERO) {
                return DRAW;
            }
            if (compareToValue < ZERO) {
                return LOSE;
            }
            throw ClassNotFoundException()
        }
    }
}
