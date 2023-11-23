package blackjack.domain

class Player(
    override val name: String,
) : Participant() {
    override val cards: Cards = Cards()

    fun isWin(): Boolean {
        if (isFinished()) {
            return false
        }
        if (Dealer.isFinished()) {
            return true
        }
        return true
    }
}
