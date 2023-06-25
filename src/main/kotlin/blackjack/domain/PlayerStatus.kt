package blackjack.domain

enum class PlayerStatus {
    STAY, BLACK_JACK, GET, END;

    companion object {
        const val BLACK_JACK_SCORE = 21
        fun status(status: PlayerStatus, score: Int): PlayerStatus {
            if (score == BLACK_JACK_SCORE) return BLACK_JACK
            if (score > BLACK_JACK_SCORE) return END
            if (status != STAY && score < BLACK_JACK_SCORE) return GET
            return STAY
        }
    }
}