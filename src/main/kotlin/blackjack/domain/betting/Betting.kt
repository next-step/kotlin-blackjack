package blackjack.domain.betting

@JvmInline
value class Betting(private val amount: Int) {
    init {
        require(amount > 0) { BETTING_VALUE_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val BETTING_VALUE_EXCEPTION_MESSAGE = "베팅 금액은 음수 일 수 없다."
    }
}
