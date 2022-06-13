package blackjack.domain

@JvmInline
value class Bet(val amount: Int) {

    init {
        require(amount > 0) { "배팅금액은 0보다 커야합니다" }
    }
}
