package domain

class Player(val name: String): Participant() {

    var bet: Money = Money.of(0)
        private set

    fun placeBet(bettingAmount: Money) {
        this.bet = bettingAmount
    }

    override fun openFirstRound(): List<Card> {
        return blackjackCards.getCards()
    }
}
