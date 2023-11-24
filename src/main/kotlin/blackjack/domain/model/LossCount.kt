package blackjack.domain.model

@JvmInline
value class LossCount private constructor(val value: Int) {
    companion object {
        fun from(count: Int): LossCount = LossCount(count)
    }
}
