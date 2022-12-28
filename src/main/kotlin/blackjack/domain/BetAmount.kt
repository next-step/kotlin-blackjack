package blackjack.domain

@JvmInline
value class BetAmount(val value: Int) {
    init {
        require(value >= 0) { "베팅 금액은 0원 이상이어야 합니다." }
    }
}
