package blackjack.domain

@JvmInline
value class Amount(val value: Double) {
    init {
        require(value >= 1) { "베팅 금액은 1 이상이어야 합니다." }
    }

    fun multiply(ratio: Double): Amount = Amount(value * ratio)
}
