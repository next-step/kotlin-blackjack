package blackjack.playingcard

data class Value(private val value: Int) {
    init {
        require(value >= 0)
    }

    fun toInt(): Int = value

    operator fun plus(other: Value): Value {
        return Value(this.value + other.value)
    }

    companion object {
        val ZERO = Value(0)
    }
}
