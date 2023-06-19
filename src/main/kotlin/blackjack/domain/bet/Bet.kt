package blackjack.domain.bet

class Bet(amount: Double = DEFAULT_AMOUNT) {

    var amount: Double = amount
        private set

    fun calculate(betResult: Double) {
        amount += betResult
    }

    companion object {
        private const val DEFAULT_AMOUNT: Double = 0.0
    }
}
