package blackjack.domain.betting

@JvmInline
value class Betting(private val amount: Int) {
    init {
        require(amount >= 0) { BETTING_VALUE_EXCEPTION_MESSAGE }
    }

    fun applyRate(rate: Double): Int {
        return (amount * rate).toInt()
    }
    companion object {
        private const val BETTING_VALUE_EXCEPTION_MESSAGE = "베팅 금액은 양수여야 합니다."
    }
}
