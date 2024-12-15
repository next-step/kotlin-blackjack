package blackjack.domain

@JvmInline
value class Bet(val value: Int) {
    operator fun plus(bet: Bet): Bet {
        return Bet(value + bet.value)
    }

    fun lose(): Bet {
        return negative(this)
    }

    fun blackjack(): Bet {
        return Bet((value * BLACKJACK_BETTING_RATE).toInt())
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        private const val BLACKJACK_BETTING_RATE = 1.5

        fun zero(): Bet {
            return Bet(0)
        }

        fun negative(bet: Bet): Bet {
            return Bet(-bet.value)
        }
    }
}
