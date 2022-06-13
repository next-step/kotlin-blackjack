package blackjack.domain.participant.vo

private const val MIN_BET_AMOUNT = 1_000

@JvmInline
value class BetAmount(
    val amount: Int
) {

    init {
        require(amount >= MIN_BET_AMOUNT) { "베팅 금액은 최소 $MIN_BET_AMOUNT 이상이어야 합니다." }
    }
}
