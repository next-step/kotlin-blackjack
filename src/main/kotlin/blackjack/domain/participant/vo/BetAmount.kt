package blackjack.domain.participant.vo

@JvmInline
value class BetAmount(
    val amount: Amount
) {
    init {
        require(amount >= MIN_BET_AMOUNT) { "베팅 금액은 최소 $MIN_BET_AMOUNT 이상이어야 합니다." }
    }

    companion object {
        private val MIN_BET_AMOUNT = Amount(1_000)

        fun of(value: Int): BetAmount = BetAmount(Amount(value))
    }
}
