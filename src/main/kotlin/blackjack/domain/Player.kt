package blackjack.domain

class Player(
    override val name: String,
) : Participant() {
    override val cards: Cards = Cards()

    fun isWin(): Boolean {
        if (isBust()) {
            return false
        }
        if (Dealer.isBust()) {
            return true
        }
        return true
    }
}
