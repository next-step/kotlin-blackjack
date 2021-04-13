package blackjack.model

data class Bet(val amount: Int) {
    operator fun times(other: Double): Bet {
        return Bet((amount * other).toInt())
    }

    operator fun unaryMinus(): Bet {
        return Bet(-amount)
    }

    operator fun plus(other: Bet): Bet {
        return Bet(amount + other.amount)
    }

    companion object {
        val ZERO = Bet(0)
    }
}
