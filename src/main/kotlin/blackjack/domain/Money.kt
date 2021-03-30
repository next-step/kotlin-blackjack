package blackjack.domain

class Money(val value: Double) {

    constructor(value: Int) : this(value.toDouble())

    operator fun times(other: Double) = Money(value = this.value * other)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Money) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "Money(value=$value)"
    }

    companion object {
        val ZERO = Money(0)
    }
}
