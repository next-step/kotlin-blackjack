package blackjack

enum class States(val state: Int) {
    HIT(1),
    BUST(2),
    BLACK_JACK(3),
    STAY(4);

    companion object {
        fun valueOf(score: Int, state: States): States {
            if (state == STAY) {
                return STAY
            }

            return valueOf(score)
        }

        private fun valueOf(score: Int): States {
            if (score < Game.BLACK_JACK_SCORE) {
                return HIT
            } else if (score == Game.BLACK_JACK_SCORE) {
                return BLACK_JACK
            }

            return BUST
        }
    }
}
