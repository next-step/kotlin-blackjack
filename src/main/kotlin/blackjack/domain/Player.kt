package blackjack.domain

open class Player(
    override val name: String,
    override val battingAmount: Int,
    override val playerCards: PlayerCards = PlayerCards()
) : Participant() {

    override fun isDealer(): Boolean {
        return false
    }

    override fun getEarnAmount(participants: List<Participant>, dealer: Dealer): Int {
        return GameResult.earnMoney(this, dealer)
    }
}
