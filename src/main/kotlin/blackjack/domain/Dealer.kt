package blackjack.domain

class Dealer(
    override val name: String,
    override val playerCards: DealerCards = DealerCards()
) : Participant() {

    override val battingAmount: Int = 0

    override fun isDealer(): Boolean {
        return true
    }

    override fun getEarnAmount(participants: List<Participant>, dealer: Dealer): Int {
        return GameResult.earnAmount(participants, this)
    }
}
