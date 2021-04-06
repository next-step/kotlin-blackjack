package blackjack.playingcard

data class Value(private val value: Int) {
    init {
        require(value >= 0)
    }

    fun toInt(): Int = value

    companion object {
        val ZERO = Value(0)
    }
}
