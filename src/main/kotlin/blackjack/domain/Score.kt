package blackjack.domain

import blackjack.controller.GameController.BLACK_JACK_SCORE

@JvmInline
value class Score private constructor(val score: Int) {

    fun isBust(): Boolean = score > BLACK_JACK_SCORE

    operator fun plus(other: Score) = from(score + other.score)

    operator fun compareTo(other: Score) = score.compareTo(other.score)

    companion object {
        fun from(score: Int): Score {
            return Score(score)
        }
    }
}
