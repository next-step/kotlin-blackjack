package blackjack.domain

class Dealer(
    dealerName: String,
    dealerCards: DealerCards = DealerCards()
) : Participant(dealerName, dealerCards) {

    override fun isDealer(): Boolean {
        return true
    }

    override fun getEarnAmount(players: List<Player>, dealer: Dealer): Int {
        return GameResult.earnAmount(players, this)
    }
}
