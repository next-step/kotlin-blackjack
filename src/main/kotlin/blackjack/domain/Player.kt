package blackjack.domain

class Player(
    override val name: String,
) : Participant() {
    override val cards: Cards = Cards()

    fun isWin(): Boolean {
        if (isFinished()) {
            return false
        }
        return true
    }
}
