package blackjack.model.playable

import blackjack.model.Referee

@JvmInline
value class BlackjackScore(
    private val value: Int
) {
    infix fun vs(other: BlackjackScore): PlayableResult {
        if (this.value == other.value) {
            return PlayableResult.DRAW
        }
        if (this.value > other.value) {
            return PlayableResult.WIN
        }
        return PlayableResult.LOSE
    }

    fun isBurst(): Boolean {
        return this.value > Referee.BLACK_JACK_SCORE
    }

    operator fun compareTo(other: BlackjackScore): Int {
        return this.value.compareTo(other.value)
    }
}
