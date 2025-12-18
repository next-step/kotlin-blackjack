package domain

class Player(val name: String): Participant() {

    private var _bettingAmount: Int = 0

    override fun openFirstRound(): List<Card> {
        return blackjackCards.getCards()
    }

    fun placeBet(bettingAmount: Int) {
        _bettingAmount = bettingAmount
    }
}
