package blackjack.domain

enum class PlayerStatus {
    RECEIVE, STAY, BUST, BLACK_JACK;

    companion object {
        const val BLACK_JACK_SCORE = 21
        fun valuesOf(score: Int, prevStatus: PlayerStatus): PlayerStatus {
            if (score == BLACK_JACK_SCORE) return BLACK_JACK
            if (score > BLACK_JACK_SCORE) return BUST
            if (prevStatus != STAY && score < BLACK_JACK_SCORE) return RECEIVE
            return STAY
        }
    }
}
