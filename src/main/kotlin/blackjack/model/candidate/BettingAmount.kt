package blackjack.model.candidate

@JvmInline
value class BettingAmount(
    val amount: Int
) {

    init {
        validateMinAmount(amount)
    }

    private fun validateMinAmount(amount: Int) = require(amount >= MIN_AMOUNT) { "베팅 금액은 최소 $MIN_AMOUNT 이상이어야 합니다." }

    companion object {
        private const val MIN_AMOUNT = 1
    }
}
