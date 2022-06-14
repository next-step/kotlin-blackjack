package blackjack.domain

open class Player(
    override val name: String,
    override val battingAmount: Int,
    override val playerCards: PlayerCards = PlayerCards()
) : Participant() {

    override fun isDealer(): Boolean {
        return false
    }
}
