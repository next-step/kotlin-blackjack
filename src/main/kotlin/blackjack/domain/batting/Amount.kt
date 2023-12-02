package blackjack.domain.batting

data class Amount(
    val value: Int,
) {
    companion object {
        fun betAmount(amount: Int): Amount {
            require(amount > 0) { "베팅 금액은 0보다 커야 합니다" }
            return Amount(amount)
        }
    }
}
