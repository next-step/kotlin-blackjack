package blackjack.domain.player

enum class PlayerStatus(val isReceivable: Boolean) {
    NOT_INIT(true), RECEIVE(true), STAY(false), BUST(false), BLACK_JACK(true);

    companion object {
        const val BLACK_JACK_SCORE = 21
        fun valuesOf(score: Int, isReceivable: Boolean): PlayerStatus {
            if (score == BLACK_JACK_SCORE) return BLACK_JACK
            if (score > BLACK_JACK_SCORE) return BUST
            if (isReceivable) return RECEIVE
            return STAY
        }
    }
}
