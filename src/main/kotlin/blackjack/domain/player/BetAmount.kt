package blackjack.domain.player

@JvmInline
value class BetAmount(private val betAmount: Int) {
    init {
        require(betAmount >= MINIMUM_BET_AMOUNT) { "베팅 금액은 $MINIMUM_BET_AMOUNT 원 이상이어야 합니다." }
    }

    val value: Int
        get() = betAmount

    companion object {
        private const val MINIMUM_BET_AMOUNT = 1_000

        fun from(value: Int): BetAmount {
            return BetAmount(value)
        }
    }
}
