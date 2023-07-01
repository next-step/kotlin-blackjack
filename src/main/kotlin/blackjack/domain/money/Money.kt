package blackjack.domain.money

@JvmInline
value class Money(val value: Int) {

    init {
        require(value > 0) {
            "money must be positive"
        }
    }

    operator fun times(other: Double): Double {
        return value * other
    }
}
