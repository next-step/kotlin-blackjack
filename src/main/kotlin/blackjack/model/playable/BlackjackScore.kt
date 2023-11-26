package blackjack.model.playable

@JvmInline
value class BlackjackScore(
    val value: Int
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
        return this.value > BLACK_JACK_SCORE
    }

    operator fun compareTo(other: BlackjackScore): Int {
        return this.value.compareTo(other.value)
    }

    companion object {
        private const val BLACK_JACK_SCORE: Int = 21
    }
}
