package domain

class Player(val name: String): Participant() {

    var bettingAmount: Int = 0

    override fun openFirstRound(): List<Card> {
        return blackjackCards.getCards()
    }

    fun placeBet(bettingAmount: Int) {
        bettingAmount.also { this.bettingAmount = it }
    }
}
