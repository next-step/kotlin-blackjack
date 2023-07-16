package blackjack.domain

@JvmInline
value class Score(private val value: Int) {

    val isBlackjack: Boolean
        get() = value == BLACKJACK_POINT_THRESHOLD

    val isBurst: Boolean
        get() = value > BLACKJACK_POINT_THRESHOLD

    val isLessThanEqualToBlackjack: Boolean
        get() = value <= BLACKJACK_POINT_THRESHOLD

    val isLessThanEqualToDealerHitThreshold: Boolean
        get() = value <= DEALER_HIT_THRESHOLD

    fun toInt() = value

    operator fun plus(other: Score): Score {
        return Score(this.value + other.value)
    }

    operator fun compareTo(other: Score): Int {
        return this.value.compareTo(other.value)
    }

    companion object {
        const val BLACKJACK_POINT_THRESHOLD = 21
        const val DEALER_HIT_THRESHOLD = 16
    }
}
