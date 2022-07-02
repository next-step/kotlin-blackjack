package blackjack.domain

@JvmInline
value class BetAmount(val value: Int) {
    init {
        require(value > 0) {
            MESSAGE_BET_AMOUNT_SHOULD_OVER_ZERO
        }
    }

    companion object {
        private const val MESSAGE_BET_AMOUNT_SHOULD_OVER_ZERO = "배팅 금액은 0보다 높아야 합니다."
    }
}
