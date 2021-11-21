package blackjack.model

@JvmInline
value class Bet(private val bet: Int) {

    init {
        require(bet >= 0)
    }

    companion object {
        val ZERO = Bet(0)
    }
}
