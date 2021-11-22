package blackjack.model

@JvmInline
value class Bet private constructor(val amount: Amount) {

    companion object {
        val ZERO = Bet(Amount.ZERO)

        fun valueOf(amount: Int): Bet {
            require(amount >= 0.0)
            return Bet(Amount(amount.toDouble()))
        }
    }
}
