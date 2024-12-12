package blackjack.core.amount

class ProfitAmount : Amount(ZERO) {
    operator fun minus(other: Amount): ProfitAmount {
        this.value -= other.amount
        return this
    }

    operator fun plus(other: Amount): ProfitAmount {
        this.value += other.amount
        return this
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        private const val ZERO = 0
    }
}
