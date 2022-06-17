package blackjack.domain

class Dealer(
    dealerName: String,
    dealerCards: DealerCards = DealerCards()
) : Participant(dealerName, dealerCards) {

    override fun isDealer(): Boolean {
        return true
    }
}
