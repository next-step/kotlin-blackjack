package blackjack.domain

data class BetAmount(val amount: Double) {
    init {
        require(amount > ZERO) { "베팅 금액은 0원 이상이어야 합니다." }
    }

    companion object {
        private const val ZERO = 0
    }
}
