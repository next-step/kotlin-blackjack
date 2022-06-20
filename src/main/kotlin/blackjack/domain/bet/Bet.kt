package blackjack.domain.bet

@JvmInline
value class Bet(private val bet: Double) {
    init {
        require(bet > 0) { BET_SHOULD_BE_ABOVE_ZERO }
    }

    companion object {
        private const val BET_SHOULD_BE_ABOVE_ZERO = "베팅 금액은 0보다 큰 숫자이여야 합니다."
    }
}
