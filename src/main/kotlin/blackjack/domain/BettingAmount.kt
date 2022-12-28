package blackjack.domain

@JvmInline
value class BettingAmount(val value: Int) {
    init {
        require(value >= 0) { "배팅 금액은 마이너스일 수 없습니다." }
    }
}
