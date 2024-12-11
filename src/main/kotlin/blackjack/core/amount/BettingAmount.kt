package blackjack.core.amount

class BettingAmount(amount: Int = 0) : Amount(amount) {
    init {
        require(amount >= 0) { ERROR_INVALID }
    }
}
