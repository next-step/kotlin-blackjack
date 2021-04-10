package blackjack.playingcard

data class Value(private val value: Int) : Comparable<Value> {
    init {
        require(value >= 0)
    }

    fun toInt(): Int = value

    operator fun plus(other: Value): Value {
        return Value(this.value + other.value)
    }

    operator fun minus(other: Value): Value {
        return Value(this.value - other.value)
    }

    override fun compareTo(other: Value): Int {
        return this.value.compareTo(other.value)
    }

    companion object {
        val ZERO = Value(0)
        val BLACKJACK_THRESHOLD = Value(21)
    }
}
