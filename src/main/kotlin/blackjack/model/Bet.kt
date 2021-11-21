package blackjack.model

@JvmInline
value class Bet(val amount: Int) {

    init {
        require(amount >= 0)
    }

    operator fun times(other: Double): Double = other * amount

    companion object {
        val ZERO = Bet(0)
    }
}
