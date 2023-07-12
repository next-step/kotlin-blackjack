package blackjack.domain.enums

import blackjack.domain.Score

enum class Condition {
    PLAY,
    STAY,
    BUST,
    BLACKJACK;

    fun from(score: Score): Condition {
        return if (score > Score.STANDARD_CARD_SCORE) {
            STAY
        } else if (score > Score.BLACK_JACK_SCORE) {
            BUST
        } else if (score == Score.BLACK_JACK_SCORE) {
            BLACKJACK
        } else {
            PLAY
        }
    }
}
