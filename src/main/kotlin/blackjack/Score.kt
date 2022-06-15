package blackjack

@JvmInline
value class Score(private val value: Int) {
    operator fun plus(other: Score): Score {
        return Score(this.value + other.value)
    }
}
