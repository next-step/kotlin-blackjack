package blackjack.domain

private const val BLACK_JACK_MULTIPLE_VALUE = 1.5

@JvmInline
value class BettingMoney(val value: Long) {
    fun getBlackJackMultipleMoney(): Long {
        return (value * BLACK_JACK_MULTIPLE_VALUE).toLong()
    }

    fun getMoney(): Long {
        return value
    }

    fun getMinusMoney(): Long {
        return -value
    }
}
