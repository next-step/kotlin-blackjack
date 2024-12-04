package blackjack.entity

class BettingAmount(val amount: Int) {
    init {
        require(amount > 0) { "베팅 금액은 0보다 커야합니다." }
    }

    fun loseIfBusted(isBusted: Boolean): Int {
        return if (isBusted) loseBet() else 0
    }

    fun loseBet() = -amount
}
