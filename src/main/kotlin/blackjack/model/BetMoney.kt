package blackjack.model

data class BetMoney(val amount: Int) {
    operator fun times(other: Double): BetMoney {
        return BetMoney((amount * other).toInt())
    }

    operator fun unaryMinus(): BetMoney {
        return BetMoney(-amount)
    }

    operator fun plus(other: BetMoney): BetMoney {
        return BetMoney(amount + other.amount)
    }

    companion object {
        val ZERO = BetMoney(0)
    }
}
