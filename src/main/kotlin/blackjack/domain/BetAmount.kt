package blackjack.domain

@JvmInline
value class BetAmount(val value: Int) {
    init {
        require(value >= MIN) { "베팅 금액은 $MIN 원 이상이어야 합니다." }
    }

    companion object {
        private const val MIN = 0
    }
}
