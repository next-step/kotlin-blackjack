package blackjack.participant

@JvmInline
value class BetingAmount(
    val amount: Int
) {
    init {
        require(amount > 0) { VALID_MESSAGE }
    }

    companion object {
        private const val VALID_MESSAGE: String = "베팅 금액은 0 이하일 수 없습니다."
    }
}
