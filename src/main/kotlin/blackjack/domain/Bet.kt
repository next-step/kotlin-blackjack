package blackjack.domain

@JvmInline
value class Bet(val amount: Int) {

    init {
        require(amount >= 0) { "배팅금액은 음수가 될 수 없습니다" }
    }

    companion object {
        val EMPTY_BET = Bet(0)
    }
}
