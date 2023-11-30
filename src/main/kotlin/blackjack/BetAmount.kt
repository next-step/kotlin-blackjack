package blackjack

@JvmInline
value class BetAmount(val amount: Int) {
    init {
        require(amount >= MINIMUM) {
            "Player need to bet more than $MINIMUM"
        }
    }

    companion object {
        const val MINIMUM = 0
    }
}
