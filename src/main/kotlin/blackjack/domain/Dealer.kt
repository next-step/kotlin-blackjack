package blackjack.domain

class Dealer(
    override val name: String,
    override val playerCards: DealerCards = DealerCards()
) : Participant() {

    override fun isDealer(): Boolean {
        return true
    }

    override fun getEarnAmount(players: List<Player>, dealer: Dealer): Int {
        return GameResult.earnAmount(players, this)
    }
}
