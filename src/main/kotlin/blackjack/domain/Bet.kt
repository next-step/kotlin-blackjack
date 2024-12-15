package blackjack.domain

@JvmInline
value class Bet(val value: Int) {
    fun apply(bet: Bet): Bet {
        return Bet(this.value + bet.value)
    }

    fun lose(): Bet {
        return Bet(0)
    }

    fun blackjack(): Bet {
        return Bet((value * BLACKJACK_BETTING_RATE).toInt())
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        private const val BLACKJACK_BETTING_RATE = 1.5
    }
}
