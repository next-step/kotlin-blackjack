package blackjack.domain

data class Money(val value: Double) {

    constructor(value: Int) : this(value.toDouble())

    operator fun times(other: Double) = Money(value = this.value * other)

    companion object {
        val ZERO = Money(0)
    }
}
