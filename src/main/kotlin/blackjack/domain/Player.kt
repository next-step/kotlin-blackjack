package blackjack.domain

class Player(
    name: String,
) : Participant(name) {
    private var _income: Int? = null

    val income: Int
        get() = _income ?: error("count has not been initialized")

    fun initIncome(betMoney: Int) {
        check(_income == null) { "count can only be initialized once" }
        _income = betMoney
    }

    fun match(dealer: Dealer) {
        val playerScore = totalScore()
        val dealerScore = dealer.totalScore()

        if (this.isTwoCardBlackJackScore()) {
            if (dealer.isTwoCardBlackJackScore()) return
            _income = (income * 0.5).toInt()
            return
        }

        if (playerScore > BLACKJACK_SCORE) {
            _income = -income
            return
        }
        if (dealerScore > BLACKJACK_SCORE) return

        if (playerScore > dealerScore) {
            _income = income
            return
        }
        if (playerScore < dealerScore) {
            _income = -income
            return
        }

        return compareCardCount(dealer)
    }

    private fun compareCardCount(dealer: Dealer) {
        _income =
            when {
                cards.size < dealer.cards.size -> income
                cards.size > dealer.cards.size -> -income
                else -> 0
            }
    }
}
