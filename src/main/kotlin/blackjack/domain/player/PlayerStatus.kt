package blackjack.domain.player

enum class PlayerStatus {
    NOT_INIT, RECEIVABLE, STAY, BUST, BLACK_JACK;

    companion object {
        const val BLACK_JACK_SCORE = 21
        fun valuesOf(score: Int, getIsReceivable: () -> Boolean): PlayerStatus {
            if (score == BLACK_JACK_SCORE) return BLACK_JACK
            if (score > BLACK_JACK_SCORE) return BUST
            if (getIsReceivable()) return RECEIVABLE
            return STAY
        }
    }
}
