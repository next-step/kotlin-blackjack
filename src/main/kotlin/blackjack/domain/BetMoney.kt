package blackjack.domain

@JvmInline
value class BetMoney(val money: Int) {
    init {
        require(money > 0) {
            "배팅은 0원보다 커야합니다."
        }
    }
}
