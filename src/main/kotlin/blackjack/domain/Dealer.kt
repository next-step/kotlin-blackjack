package blackjack.domain

class Dealer(
    override val name: String,
    override val playerCards: DealerCards = DealerCards()
) : Participant() {
    override val battingAmount: Int = 0

    override fun isDealer(): Boolean {
        return true
    }

    companion object {
        private const val SCORE_TO_REQUEST_A_CARD_FOR_DEALER = 16
    }
}
